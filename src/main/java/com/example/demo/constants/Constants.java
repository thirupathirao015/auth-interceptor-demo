
package com.example.demo.constants;
import java.util.List;

public class Constants {
    public static final List<String> BYPASS_AUTH_URLS = List.of(
            "/auth",
            "/public",
            "/swagger",
            "/v3/api-docs"
    );
}
