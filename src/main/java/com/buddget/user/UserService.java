package com.buddget.user;

import com.buddget.entities.User;
import com.buddget.repositories.RoleRepository;
import com.buddget.user.auth.UserAuthDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
//
//    @Transactional(readOnly = true)
//    public Page<UserDTO> findAllPaged(Pageable pageable) {
//        Page<User> list = userRepository.findAll(pageable);
//        return list.map(x -> new UserDTO(x));
//    }
//
//    @Transactional(readOnly = true)
//    public UserDTO findById(Long id) {
//        Optional<User> object = userRepository.findById(id);
//        //User user = obj.get();
//        User user = object.orElseThrow(() -> new ResourceNotFoundException("ID '" + id + "' not found"));
//        return new UserDTO(user);
//    }

    @Transactional
    public User signUp(String firstName, String lastName, String email, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setLastLogin(null);
        user.setPassword(passwordEncoder.encode(password));
        user = userRepository.save(user);
        return user;
    }
//
//    @Transactional
//    public UserDTO update(Long id, UserUpdateDTO dto) {
//        try {
//            User user = userRepository.getReferenceById(id);
//            copyDtoToEntity(dto, user);
//            user = userRepository.save(user);
//            return new UserDTO(user);
//        }
//        catch (EntityNotFoundException e) {
//            throw new ResourceNotFoundException("ID '" + id + "' not found");
//        }
//    }

    public void delete(Long id) {
        try {
            if (userRepository.existsById(id)) {userRepository.deleteById(id);}
            else {throw new ResourceNotFoundException("ID '" + id + "' not found");}
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integrity violation");
        }
    }

//    private void copyDtoToEntity(UserDTO.Request.SignUp dto, User user){
//
//
//        user.getRoles().clear();
//        for(RoleDTO roleDto : dto.getRoles()){
//            Role role = roleRepository.getReferenceById(roleDto.getId());
//            user.getRoles().add(role);
//        }
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null){
            logger.error("User not found: " + username);
            throw new UsernameNotFoundException("Email not found");
        }
        logger.info("User found: " + username);
        return user;
    }
}
