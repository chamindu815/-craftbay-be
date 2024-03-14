package com.craftbay.crafts.controller;

import com.craftbay.crafts.dto.forgotpassword.ForgotPasswordRequestDto;
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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginReq loginReq)  {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));
            String email = authentication.getName();

            User user = new User(email,"");
            String token = jwtUtil.createToken(user);
            User loggedUser = userService.getUserByUsername(email);

            LoginRes loginRes = new LoginRes(email,token, loggedUser.getId());

            return ResponseEntity.ok(loginRes);

        }catch (BadCredentialsException e){
            ErrorRes errorResponse = new ErrorRes(HttpStatus.UNAUTHORIZED,"Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }catch (Exception e){
            ErrorRes errorResponse = new ErrorRes(HttpStatus.UNAUTHORIZED, "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    @CrossOrigin
    @PostMapping("/save")
    public String saveUser(@Valid @RequestBody RegisterRequestDto request){
        String id = userService.registerUser(request);
        return id;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

    @CrossOrigin
    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestBody ForgotPasswordRequestDto request) {
        userService.forgotPassword(request);
    }
}
