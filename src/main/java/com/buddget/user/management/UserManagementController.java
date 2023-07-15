package com.buddget.user.management;


import com.buddget.entities.User;
import com.buddget.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users")
public class UserManagementController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDetailsResponse>> findAllPaged(Pageable pageable) {
        Page<User> list = userService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list.map(user -> new UserDetailsResponse(user.getFirstName(),
                user.getLastName(), user.getEmail(), user.getDateCreated(), user.getLastLogin(), user.getRoles())));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDetailsResponse> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserDetailsResponse(user.getFirstName(),
                user.getLastName(), user.getEmail(), user.getDateCreated(), user.getLastLogin(), user.getRoles()));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDetailsResponse> update(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest payload) {
        User user = userService.update(id, payload.firstName(), payload.lastName(), payload.email(), payload.password(), payload.roles());
        return  ResponseEntity.ok().body(new UserDetailsResponse(user.getFirstName(),
                user.getLastName(), user.getEmail(), user.getDateCreated(), user.getLastLogin(), user.getRoles()));
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
