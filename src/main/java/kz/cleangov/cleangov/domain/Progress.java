package kz.cleangov.cleangov.domain;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kz.cleangov.cleangov.service.ProgressService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "progressTable")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Progress {
    @Id
    @UuidGenerator
    @Column(name = "id_progress", unique = true, updatable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "id_task", nullable = false)
    private Tasks task;

    @Column(name = "progress", nullable = false)
    private int progress;

    private boolean completed;

    public void setProgress(int progress, ProgressService progressService) {
        this.progress = progress;
        boolean wasCompleted = this.completed;
    
        this.completed = progress == 100;
    
        if (!wasCompleted && this.completed) {
            progressService.handleCompletedProgress(this);
        }
    }

}
