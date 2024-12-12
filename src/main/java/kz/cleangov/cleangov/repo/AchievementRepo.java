package kz.cleangov.cleangov.repo;

import kz.cleangov.cleangov.domain.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepo extends JpaRepository<Achievement, Long> {
    Achievement findByName(String name);
}
    