
package com.example.demo.interceptor;

import com.example.demo.constants.Constants;
import com.example.demo.service.OAuthService;
import com.example.demo.service.TokenService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final OAuthService oauthService;
    private final TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        String uri = request.getRequestURI();

        if (Constants.BYPASS_AUTH_URLS.stream().anyMatch(uri::contains)) {
            return true;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null) {
            throw new RuntimeException("Missing Authorization Header");
        }
        System.out.println("RAW HEADER: " + authHeader);

        String token = authHeader.replace("Bearer ", "");

        System.out.println("EXTRACTED TOKEN: " + token);

        Claims claims = tokenService.validateToken(token);
        String scope = (String) claims.get("scope");

        List<String> callerScopes = List.of(scope.split(" "));
        List<String> allowedScopes = oauthService.getRequestedApi(uri);

        if (allowedScopes.stream().noneMatch(callerScopes::contains)) {
            throw new RuntimeException("Forbidden - Scope mismatch");
        }

        return true;
    }
}
