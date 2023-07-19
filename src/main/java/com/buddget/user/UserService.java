package com.buddget.user;

import com.buddget.email.EmailService;
import com.buddget.entities.EmailToken;
import com.buddget.entities.Role;
import com.buddget.entities.User;
import com.buddget.repositories.EmailTokenRepository;
import com.buddget.repositories.RoleRepository;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmailTokenRepository emailTokenRepository;

    @Autowired
    private EmailService emailService;

    @Transactional(readOnly = true)
    public Page<User> findAllPaged(Pageable pageable) {
        Page<User> list = userRepository.findAll(pageable);
        return list;
    }

//    @Transactional(readOnly = true)
//    public User findById(UUID id) {
//        Optional<User> object = userRepository.findById(id);
//        //User user = obj.get();
//        User user = object.orElseThrow(() -> new ResourceNotFoundException("ID '" + id + "' not found"));
//        return user;
//    }

    @Transactional
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {return user;}
        throw new ResourceNotFoundException("User '" + email + "' not found");
    }

    @Transactional
    public void signIn(String email){
        User user = userRepository.getReferenceByEmail(email);
        user.setLastLogin(Instant.now());
        userRepository.save(user);
    }

    @Transactional
    public String signUp(String firstName, String lastName, String email, String password) throws MessagingException {

        Role userRole = roleRepository.findByAuthority("ROLE_OPERATOR");
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setLastLogin(null);
        user.setPassword(passwordEncoder.encode(password));
        user.getRoles().add(userRole);
        user = userRepository.save(user);

        String token = UUID.randomUUID().toString();
        EmailToken emailToken = new EmailToken(
                token, Instant.now(),
                Instant.now().plus(Duration.ofMinutes(15)),
                user);

        emailToken = emailTokenRepository.save(emailToken);

        String link = "http://localhost:8080/auth/confirm?token=" + token;
        emailService.sendEmailConfirmation(
                user.getEmail(),
                emailService.buildEmail(
                        user.getFirstName(), link));

        return token;
    }

    @Transactional
    public User update(String email, String newEmail, String firstName, String lastName, String password, Set<String> roles) {
        try {
            User user = userRepository.getReferenceByEmail(email);
            user.getRoles().clear();
            for (String role : roles) {
                Role userRole = roleRepository.findByAuthority(role);
                user.getRoles().add(userRole);
            }
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(newEmail);
            user.setLastLogin(null);
            user.setPassword(passwordEncoder.encode(password));
            user = userRepository.save(user);
            return user;
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("User '" + email + "' not found");
        }
    }

    public void delete(UUID id) {
        try {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
            } else {
                throw new ResourceNotFoundException("ID '" + id + "' not found");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integrity violation");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            logger.error("User not found: " + username);
            throw new UsernameNotFoundException("Email not found");
        }
        logger.info("User found: " + username);
        return user;
    }

    @Transactional
    public void enableAppUser(String email) {
        User user = userRepository.getReferenceByEmail(email);
        user.setEnabled(true);
        userRepository.save(user);
    }
}
