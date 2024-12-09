package kz.cleangov.cleangov.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "testResultTable")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    private int successPercentage;
}
