package kz.cleangov.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kz.cleangov.cleangov.domain.Users;
import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<Users, String> {
    Optional<Users> findById(String id);

    Users findByUsername(String username);
    
} 
