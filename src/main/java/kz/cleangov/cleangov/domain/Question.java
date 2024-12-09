package kz.cleangov.cleangov.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "questionTable")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String textRu;
    private String textKz;
    private String textEn;

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;
}
