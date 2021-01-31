package com.github.ultraultimated.photoapp.api.users.photoappapiusers.service;

import com.github.ultraultimated.photoapp.api.users.photoappapiusers.shared.UserDTO;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setUserId(UUID.randomUUID().toString());
        return null;
    }
}
