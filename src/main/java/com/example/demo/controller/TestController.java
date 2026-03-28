
package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/public/hello")
    public String publicApi() {
        return "Public API - No Auth";
    }

    @GetMapping("/secure")
    public String secureApi() {
        return "Secure API - Authenticated";
    }

    @GetMapping("/admin")
    public String adminApi() {
        return "Admin API - Requires ADMIN scope";
    }
}
