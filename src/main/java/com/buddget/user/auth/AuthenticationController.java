package com.buddget.user.auth;

import com.buddget.entities.User;
import com.buddget.user.EmailTokenService;
import com.buddget.user.TokenService;
import com.buddget.user.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailTokenService emailTokenService;

    @PostMapping("/login")
    public ResponseEntity<UserSignInResponse> login(@RequestBody @Valid UserSignInRequest payload) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(payload.email(), payload.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateJwtToken((User) auth.getPrincipal());
        userService.signIn(payload.email());
        return ResponseEntity.ok(new UserSignInResponse(token));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserSignUpResponse> signUp(@RequestBody @Valid UserSignUpRequest payload) throws MessagingException {
        return ResponseEntity.ok().body(new UserSignUpResponse(userService.signUp(payload.firstName(), payload.lastName(), payload.email(),
                payload.password())));
    }

    @GetMapping(path = "confirm")
    public ResponseEntity<String> confirm(@RequestParam("token") String token) {
        return ResponseEntity.ok().body(emailTokenService.confirmEmailToken(token));
    }
}
