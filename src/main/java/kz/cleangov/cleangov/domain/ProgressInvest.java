package kz.cleangov.cleangov.domain;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "progressInvestTable")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgressInvest {

    @Id
    @UuidGenerator
    @Column(name = "id_progressInv", unique = true, updatable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "id_investigation", nullable = false)
    private Investigations investigation;

    @Column(name = "progress", nullable = false)
    private int progress;

    @Column(name = "completed", nullable = false)
    private boolean completed;

    public void addProgress(int points) {
        this.progress = Math.min(100, this.progress + points);
        this.completed = this.progress == 100;
    }
}

