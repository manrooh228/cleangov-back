package kz.cleangov.cleangov.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "answersTable")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Answers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String textRu;
    private String textKz;
    private String textEn;
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
}
