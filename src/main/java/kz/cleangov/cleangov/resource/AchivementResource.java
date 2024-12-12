package kz.cleangov.cleangov.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kz.cleangov.cleangov.domain.AchievementUser;
import kz.cleangov.cleangov.domain.Users;
import kz.cleangov.cleangov.repo.AchievementUserRepo;
import kz.cleangov.cleangov.repo.UserRepo;
import kz.cleangov.cleangov.service.AchievementService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/achievements")
@RequiredArgsConstructor
public class AchivementResource {

    private final AchievementService achievementService;
private final AchievementUserRepo achievementUserRepo;
    private final UserRepo usersRepo;

    // Получение достижений пользователя по ID
    @GetMapping("/{userId}/achievements")
    public List<AchievementResponse> getUserAchievements(@PathVariable String userId) {
        Users user = usersRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        List<AchievementUser> achievementUsers = achievementUserRepo.findByUser(user);

        return achievementUsers.stream()
                .map(achievementUser -> new AchievementResponse(
                        achievementUser.getAchievement().getName(),
                        achievementUser.isAchieved()
                ))
                .collect(Collectors.toList());
    }

    // DTO для ответа
    public static class AchievementResponse {
        private String name;
        private boolean achieved;

        public AchievementResponse(String name, boolean achieved) {
            this.name = name;
            this.achieved = achieved;
        }

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isAchieved() {
            return achieved;
        }

        public void setAchieved(boolean achieved) {
            this.achieved = achieved;
        }
    }
    @PostMapping("/check")
    public void checkAchievement(@RequestBody AchievementCheckRequest request) {
        // Проверка и присвоение ачивки пользователю
        Users user = new Users();
        user.setId(request.getUserId());
        achievementService.checkAndAwardAchievement(user, request.getAchievementName());
    }

    @Getter
    public static class AchievementCheckRequest {
        private String userId;
        private String achievementName;

        // Getters and Setters
    }
}