package kz.cleangov.cleangov.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "testTable")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameRu;
    private String nameKz;
    private String nameEn;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Tasks task;
}
