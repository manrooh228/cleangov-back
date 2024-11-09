package kz.cleangov.cleangov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @SuppressWarnings({ "removal", "deprecation" })
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors() // Включаем CORS в Spring Security
            .and()
            .csrf().disable() // Отключаем CSRF, если это необходимо
            .authorizeRequests()
            .anyRequest().permitAll(); // Или настройте нужные разрешения

        return http.build();
    }
}
