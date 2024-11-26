package kz.cleangov.cleangov.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kz.cleangov.cleangov.domain.Users;
import kz.cleangov.cleangov.exception.InvalidCredentialsException;
import kz.cleangov.cleangov.exception.UserAlreadyExistsException;
import kz.cleangov.cleangov.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Users> login(@RequestBody UserLoginRequest request) {
        try {
            Users user = userService.authenticateUser(request.getUsername(), request.getPassword());
            return ResponseEntity.ok(user);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    
    @GetMapping("/check/{username}")
    public ResponseEntity<String> checkUserExists(@PathVariable String username) {
        try {
            String result = userService.checkUserExists(username);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users newUser) {
        try {
            userService.registerUser(newUser);
            return ResponseEntity.ok("User registered successfully!");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    } 
}
