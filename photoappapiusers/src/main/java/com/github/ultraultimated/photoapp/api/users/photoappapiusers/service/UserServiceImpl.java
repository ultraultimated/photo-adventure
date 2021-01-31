package com.github.ultraultimated.photoapp.api.users.photoappapiusers.service;

import com.github.ultraultimated.photoapp.api.users.photoappapiusers.data.UserCrudRepository;
import com.github.ultraultimated.photoapp.api.users.photoappapiusers.data.UserEntity;
import com.github.ultraultimated.photoapp.api.users.photoappapiusers.shared.UserDTO;
import com.netflix.discovery.converters.Auto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserCrudRepository repository;

    @Autowired
    public UserServiceImpl(UserCrudRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setUserId(UUID.randomUUID().toString());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        userEntity.setEncryptedPassword("test");

        repository.save(userEntity);

        return null;
    }
}
