package kz.cleangov.cleangov.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

// import static org.springframework.http.HttpHeaders.ACCEPT;
// import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS;
// import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;
// import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS;
// import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD;
// import static org.springframework.http.HttpHeaders.AUTHORIZATION;
// import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
// import static org.springframework.http.HttpHeaders.ORIGIN;
// import static org.springframework.http.HttpMethod.DELETE;
// import static org.springframework.http.HttpMethod.GET;
// import static org.springframework.http.HttpMethod.OPTIONS;
// import static org.springframework.http.HttpMethod.PATCH;
// import static org.springframework.http.HttpMethod.POST;
// import static org.springframework.http.HttpMethod.PUT;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedHeaders(List.of("Origin", "Content-Type", "Accept", "Authorization"));
        config.setExposedHeaders(List.of("Authorization"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}


