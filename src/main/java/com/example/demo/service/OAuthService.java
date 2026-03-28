
package com.example.demo.service;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OAuthService {
    public List<String> getRequestedApi(String uri) {
        if (uri.contains("/admin")) {
            return List.of("ADMIN");
        }
        return List.of("READ");
    }
}
