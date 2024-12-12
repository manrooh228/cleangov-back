package kz.cleangov.cleangov.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import kz.cleangov.cleangov.domain.Achievement;
import kz.cleangov.cleangov.domain.AchievementUser;
import kz.cleangov.cleangov.domain.Users;
import kz.cleangov.cleangov.repo.AchievementRepo;
import kz.cleangov.cleangov.repo.AchievementUserRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AchievementService {
     private final AchievementRepo achievementRepo;
    private final AchievementUserRepo achievementUserRepo;

    public void checkAndAwardAchievement(Users user, String achievementName) {
        Achievement achievement = achievementRepo.findByName(achievementName);

        if (achievement == null) {
            throw new IllegalArgumentException("Achievement not found: " + achievementName);
        }

        achievementUserRepo.findByUserAndAchievementName(user, achievementName)
            .ifPresentOrElse(
                achievementUser -> {
                    // Ачивка уже получена
                },
                () -> {
                    // Присваиваем ачивку
                    AchievementUser newAchievementUser = new AchievementUser();
                    newAchievementUser.setUser(user);
                    newAchievementUser.setAchievement(achievement);
                    newAchievementUser.setAchieved(true);
                    newAchievementUser.setAchievedAt(LocalDateTime.now());

                    achievementUserRepo.save(newAchievementUser);
                }
            );
    }
}
