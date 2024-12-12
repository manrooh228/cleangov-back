package kz.cleangov.cleangov.repo;

import kz.cleangov.cleangov.domain.AchievementUser;
import kz.cleangov.cleangov.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AchievementUserRepo extends JpaRepository<AchievementUser, Long> {
    Optional<AchievementUser> findByUserAndAchievementName(Users user, String achievementName);
}