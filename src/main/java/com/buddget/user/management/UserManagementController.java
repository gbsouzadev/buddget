package com.buddget.user.management;


import com.buddget.entities.User;
import com.buddget.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/users")
public class UserManagementController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserResponse>> findAllPaged(Pageable pageable) {
        Page<User> list = userService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list.map(user -> new UserResponse(user.getId(), user.getFirstName(),
                user.getLastName(), user.getEmail(), user.getDateCreated(), user.getLastLogin(), user.getRoles(),
                user.getLocked(), user.getEnabled())));
    }

//    @GetMapping(value = "/{id}")
//    public ResponseEntity<UserResponse> findById(@PathVariable UUID id) {
//        User user = userService.findById(id);
//        return ResponseEntity.ok().body(new UserResponse(user.getId(), user.getFirstName(),
//                user.getLastName(), user.getEmail(), user.getDateCreated(), user.getLastLogin(), user.getRoles()));
//    }

    @PostMapping("/find-by-email")
    public ResponseEntity<UserResponse> findByEmail(@Valid @RequestBody UserFindRequest payload) {
        System.out.println("Entrou no controller");
        User user = userService.findByEmail(payload.email());
        System.out.println("Voltou do controller");
        return ResponseEntity.ok().body(new UserResponse(user.getId(), user.getFirstName(),
                user.getLastName(), user.getEmail(), user.getDateCreated(), user.getLastLogin(), user.getRoles(),
                user.getLocked(), user.getEnabled()));
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponse> update(@Valid @RequestBody UserUpdateRequest payload) {
        User user = userService.update(payload.email(), payload.newEmail(), payload.firstName(), payload.lastName(), payload.password(), payload.roles());
        return  ResponseEntity.ok().body(new UserResponse(user.getId(), user.getFirstName(),
                user.getLastName(), user.getEmail(), user.getDateCreated(), user.getLastLogin(), user.getRoles(),
                user.getLocked(), user.getEnabled()));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
