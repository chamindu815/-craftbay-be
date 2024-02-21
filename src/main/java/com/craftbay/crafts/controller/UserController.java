package com.craftbay.crafts.controller;


import com.craftbay.crafts.dto.user.UserDto;
import com.craftbay.crafts.util.enums.ErrorMessages;
import org.modelmapper.ModelMapper;
import com.craftbay.crafts.exception.UserServiceException;
import com.craftbay.crafts.model.request.UserDetailsRequestModal;
import com.craftbay.crafts.service.UserService;
import com.craftbay.crafts.model.ui.UserRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public UserRest createUser(@RequestBody UserDetailsRequestModal userDetails) throws Exception
    {
        if (userDetails.getEmail().isEmpty())
        {
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
        }

        ModelMapper modelMapper = new ModelMapper();
        UserDto userDTO = modelMapper.map(userDetails, UserDto.class);

        UserDto createdUserDTO = userService.createUser(userDTO);

        UserRest userRest = modelMapper.map(createdUserDTO, UserRest.class);
        return userRest;
    }
}
