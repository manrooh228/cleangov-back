package kz.cleangov.cleangov.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgressRequest {
    private String userId;
    private String taskId;
    private int progress;
}
