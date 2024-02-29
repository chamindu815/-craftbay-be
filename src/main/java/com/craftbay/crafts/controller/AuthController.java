package com.craftbay.crafts.controller;

import com.craftbay.crafts.dto.login.ErrorRes;
import com.craftbay.crafts.dto.login.LoginReq;
import com.craftbay.crafts.dto.login.LoginRes;
import com.craftbay.crafts.dto.register.RegisterRequestDto;
import com.craftbay.crafts.entity.user.User;
import com.craftbay.crafts.service.UserService;
import com.craftbay.crafts.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;

    }

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginReq loginReq)  {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));
            String email = authentication.getName();
            User user = new User(email,"");
            String token = jwtUtil.createToken(user);
            LoginRes loginRes = new LoginRes(email,token);

            return ResponseEntity.ok(loginRes);

        }catch (BadCredentialsException e){
            ErrorRes errorResponse = new ErrorRes(HttpStatus.UNAUTHORIZED,"Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }catch (Exception e){
            ErrorRes errorResponse = new ErrorRes(HttpStatus.UNAUTHORIZED, "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    @PostMapping("/save")
    public String saveUser(@RequestBody RegisterRequestDto request){
        String id = userService.registerUser(request);
        return id;
    }
}
