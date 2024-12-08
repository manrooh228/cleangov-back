package kz.cleangov.cleangov.domain;


import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tasksTable")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tasks {
    @Id
    @UuidGenerator
    @Column(name = "id_task", unique = true, updatable = false)
    private String id;
    private String name;
    private String description;
    private String tasktype; // видеоурок(video)/тест(test)/рефлексия(reflexio)
    
    @ManyToOne
    @JoinColumn(name = "id_investigation", nullable = false)
    private Investigations investigation;
}
