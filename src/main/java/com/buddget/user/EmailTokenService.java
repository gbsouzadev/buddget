package com.buddget.user;

import com.buddget.entities.EmailToken;
import com.buddget.entities.User;
import com.buddget.repositories.EmailTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Service
public class EmailTokenService {

    @Autowired
    private EmailTokenRepository emailTokenRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public String confirmEmailToken(String token) throws IllegalStateException {
        EmailToken emailToken = emailTokenRepository.findByToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("Token not found"));

        if (emailToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Email already confirmed");
        }

        Instant expiredAt = emailToken.getExpiresAt();

        if (expiredAt.isBefore(Instant.now())) {
            throw new IllegalStateException("Token expired");
        }

        emailToken.setConfirmedAt(Instant.now());
        emailTokenRepository.save(emailToken);


        userService.enableAppUser(emailToken.getUser().getEmail());
        return "confirmed";
    }

}
