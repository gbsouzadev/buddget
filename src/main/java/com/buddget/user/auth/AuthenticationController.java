package com.buddget.user.auth;

import com.buddget.entities.User;
import com.buddget.user.TokenService;
import com.buddget.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserSignInResponse> login(@RequestBody @Valid UserSignInRequest payload) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(payload.email(), payload.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        userService.signIn(payload.email());
        return ResponseEntity.ok(new UserSignInResponse(token));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserSignUpResponse> login(@RequestBody @Valid UserSignUpRequest payload) {
        User user = userService.signUp(payload.firstName(), payload.lastName(), payload.email(), payload.password());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserSignUpResponse(user.getId()));
    }
}
