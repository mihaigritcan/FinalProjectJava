package com.sda.app.controller;

import com.sda.app.entity.Item;
import com.sda.app.entity.User;
import com.sda.app.service.UserService;
import com.sda.app.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users") //adresa unde gasim metodele pentru user

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllUsers() {
        List<User> userList = this.userService.findAll();
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("User List successfully fetched")
                .data(userList)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "is working";
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createUser(@RequestBody User user) {
        User usr= new User();
        usr.setUsername(user.getUsername());
        usr.setEmail(user.getEmail());
        usr.setPassword(user.getPassword());
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("User successfully created")
                .data(userService.createUser(usr))
                .build();
        return ResponseEntity.ok(response);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody User user,@PathVariable("id") Integer id) {
        User usr= new User();
        usr.setId(id);
        usr.setUsername(user.getUsername());
        usr.setEmail(user.getEmail());
        usr.setPassword(user.getPassword());
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("User successfully updated")
                .data(userService.updateUser(usr))
                .build();
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("User successfully deleted")
                .data(null)
                .build();
        return ResponseEntity.ok(response);

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody User user)
    {
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("User successfully logged in")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody User user){
       /* return createUser(user);*/
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("User successfully registered")
                .data(this.userService.createUser(user))
                .build();
        return ResponseEntity.ok(response);
    }

}

