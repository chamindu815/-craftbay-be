package com.craftbay.crafts.service.impl;

import com.craftbay.crafts.util.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.craftbay.crafts.dto.user.UserDto;
import com.craftbay.crafts.repository.UserRepository;
import com.craftbay.crafts.service.UserService;
//import com.craftbay.crafts.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    Utils utils;

    @Override
    public UserDto createUser(UserDto userDto) {
         com.craftbay.crafts.entity.User userByEmail = userRepository.findByEmail(userDto.getEmail());
        if (userByEmail != null)
        {
            throw new RuntimeException("Record already exists");
        }

        com.craftbay.crafts.entity.User user = new com.craftbay.crafts.entity.User();
        BeanUtils.copyProperties(userDto, user);

        String publicUserId = utils.generateUserId(20);
        user.setUserId(publicUserId);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        com.craftbay.crafts.entity.User storedUserEntity = userRepository.save(user);

        UserDto returnUserDTO = new UserDto();
        BeanUtils.copyProperties(storedUserEntity, returnUserDTO);

        return returnUserDTO;
    }

    @Override
    public UserDto getUser(String email) {
        com.craftbay.crafts.entity.User user = userRepository.findByEmail(email);
        if (user == null)
        {
            throw new UsernameNotFoundException(email);
        }
        UserDto returnUserDTO = new UserDto();
        BeanUtils.copyProperties(user, returnUserDTO);
        return returnUserDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.craftbay.crafts.entity.User user = userRepository.findByEmail(email);
        if (user == null)
        {
            throw new UsernameNotFoundException(email);
        }
        return new User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    }


//    @Override
//    public String addUser(UserDto userDto) {
//
//        User user = new User(
//                userDto.getId(),
//                userDto.getName(),
//                userDto.getEmail(),
//                userDto.getPhone(),
//                this.passwordEncoder.encode(userDto.getPassword())
//        );
//
//        userRepository.save(user);
//
//        return user.getName();
//    }
//
//    @Override
//    public LoginResponse loginUser(LoginDto loginDto) {
//        String msg = "";
//        User user1 = userRepository.findByEmail(loginDto.getEmail());
//        if (user1 != null) {
//            String password = loginDto.getPassword();
//            String encodedPassword = user1.getPassword();
//            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
//            if (isPwdRight) {
//                Optional<User> user = userRepository.findOneByEmailAndPassword(loginDto.getEmail(), encodedPassword);
//                if (user.isPresent()) {
//                    return new LoginResponse("Login Success", true);
//                } else {
//                    return new LoginResponse("Login Failed", false);
//                }
//            } else {
//                return new LoginResponse("Email not exits", false);
//            }
//        }else {
//            return new LoginResponse("Email not exits", false);
//        }
//
//    }

}
