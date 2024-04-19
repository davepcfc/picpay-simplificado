package pacifico.davidson.picpaysimplificado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pacifico.davidson.picpaysimplificado.domain.user.User;
import pacifico.davidson.picpaysimplificado.domain.user.UserDTO;
import pacifico.davidson.picpaysimplificado.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User newUser = service.createUser(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        var users = service.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
