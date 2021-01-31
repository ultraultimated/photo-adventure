package com.github.ultraultimated.photoapp.api.users.photoappapiusers.ui.controller;

import com.github.ultraultimated.photoapp.api.users.photoappapiusers.service.UserService;
import com.github.ultraultimated.photoapp.api.users.photoappapiusers.shared.UserDTO;
import com.github.ultraultimated.photoapp.api.users.photoappapiusers.ui.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private Environment environment;

    @Autowired
    private UserService userService;

    @GetMapping("/status/check")
    public String status(){
        return "working on prot number: " + environment.getProperty("local.server.port");
    }

    @PostMapping
    public String createUser(@Valid @RequestBody User user)
    {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userService.createUser(userDTO);
        return "User created successfully";
    }

}
