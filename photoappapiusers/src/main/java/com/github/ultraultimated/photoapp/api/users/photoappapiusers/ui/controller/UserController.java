package com.github.ultraultimated.photoapp.api.users.photoappapiusers.ui.controller;

import com.github.ultraultimated.photoapp.api.users.photoappapiusers.ui.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private Environment environment;

    @GetMapping("/status/check")
    public String status(){
        return "working on prot number: " + environment.getProperty("local.server.port");
    }

    @PostMapping
    public String createUser(@Valid @RequestBody User user)
    {
        return "User created successfully";
    }

}
