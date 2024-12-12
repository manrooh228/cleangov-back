package kz.cleangov.cleangov.domain;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "achievementUserTable")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AchievementUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "achievement_id", nullable = false)
    private Achievement achievement;

    private boolean achieved;

    private LocalDateTime achievedAt;
}
