package com.github.ultraultimated.photoapp.api.users.photoappapiusers.ui.controller;

import com.github.ultraultimated.photoapp.api.users.photoappapiusers.service.UserService;
import com.github.ultraultimated.photoapp.api.users.photoappapiusers.shared.UserDTO;
import com.github.ultraultimated.photoapp.api.users.photoappapiusers.ui.model.CreateUserResponseModel;
import com.github.ultraultimated.photoapp.api.users.photoappapiusers.ui.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public String status() {
        return "working on prot number: " + environment.getProperty("local.server.port");
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            System.out.println(userDTO.getPassword());
            UserDTO responseUserService = userService.createUser(userDTO);
            CreateUserResponseModel responseModel = modelMapper.map(responseUserService, CreateUserResponseModel.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
