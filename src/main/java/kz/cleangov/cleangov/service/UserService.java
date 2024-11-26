package kz.cleangov.cleangov.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kz.cleangov.cleangov.domain.Users;
import kz.cleangov.cleangov.exception.InvalidCredentialsException;
import kz.cleangov.cleangov.exception.UserAlreadyExistsException;
import kz.cleangov.cleangov.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public Users authenticateUser(String username, String password ){
        try {
            Users user = userRepo.findByUsername(username);

            if(user == null || !user.getPassword().equals(password)) {
                throw new InvalidCredentialsException("Invalid username or password.");
            }
            
            return user;
        } catch (Exception e) {
            log.error("Authentication error: {}", e.getMessage());
            throw e;
        }
    }

    public String checkUserExists(String username) {

        Users user = userRepo.findByUsername(username);
        if (user != null) {
            throw new UserAlreadyExistsException("Invalid username or password");
        }
        return "User does not exists. Please proceed to registration.";
    }

    public void registerUser(Users newUser) {
        Users existUser = userRepo.findByUsername(newUser.getUsername());

        if(existUser != null) {
            throw new UserAlreadyExistsException("This username already have been taken");
        }

        userRepo.save(newUser);
    }
}
