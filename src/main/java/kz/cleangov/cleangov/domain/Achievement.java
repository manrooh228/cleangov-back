package kz.cleangov.cleangov.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "achievementTable")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
}
