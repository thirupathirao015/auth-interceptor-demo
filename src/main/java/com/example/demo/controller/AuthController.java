
package com.example.demo.controller;

import com.example.demo.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TokenService tokenService;

    @PostMapping("/token")
    public Map<String, Object> getToken(
            @RequestParam("client_id") String clientId,
            @RequestParam("client_secret") String clientSecret,
            @RequestParam("grant_type") String grantType,
            @RequestParam(value = "scope", required = false) String scope) {

        // 1. Validate grant type
        if (!"client_credentials".equals(grantType)) {
            throw new RuntimeException("Invalid grant type");
        }

        // 2. Validate client
        if (!"my-client".equals(clientId) || !"secret123".equals(clientSecret)) {
            throw new RuntimeException("Invalid client credentials");
        }

        // 3. Default scope if null
        if (scope == null) {
            scope = "READ";
        }

        // 4. Generate token
        String token = tokenService.generateToken(clientId, scope);

        // 5. OAuth standard response
        return Map.of(
                "access_token", token,
                "token_type", "Bearer",
                "expires_in", 3600
        );
    }
}
