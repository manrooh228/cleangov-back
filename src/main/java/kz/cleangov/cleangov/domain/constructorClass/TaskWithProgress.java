package kz.cleangov.cleangov.domain.constructorClass;

import kz.cleangov.cleangov.domain.Tasks;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskWithProgress {
    private String id;
    private String name;
    private String description;
    private String taskType;
    private int progress;

    public TaskWithProgress(Tasks task, int progress) {
        this.id = task.getId();
        this.name = task.getName();
        this.description = task.getDescription();
        this.taskType = task.getTasktype();
        this.progress = progress;
    }
}

