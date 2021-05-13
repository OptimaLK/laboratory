package ru.optima.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.optima.repr.UserRepr;
import ru.optima.persist.model.User;
import ru.optima.service.UserServiceImpl;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class RestUserController {
    private final UserServiceImpl userServiceImpl;

    @GetMapping("/all")
    public ResponseEntity<List<UserRepr>> getallUsers() {
        List<UserRepr> users = userServiceImpl.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userServiceImpl.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(User user) {
        User u = userServiceImpl.addUser(user);
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(User user) {
        User u = userServiceImpl.updateUser(user);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userServiceImpl.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
