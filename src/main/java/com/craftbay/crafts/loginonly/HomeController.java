package com.craftbay.crafts.loginonly;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/rest/home")
    private String home() {
        return "Home";
    }

    @GetMapping("/rest/admin")
    private String admin() {
        return "admin";
    }

    @GetMapping("/rest/user")
    private String user() {
        return "user";
    }
}
