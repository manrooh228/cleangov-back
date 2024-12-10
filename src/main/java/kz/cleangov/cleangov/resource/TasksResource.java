package kz.cleangov.cleangov.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kz.cleangov.cleangov.domain.constructorClass.TaskWithProgress;
import kz.cleangov.cleangov.service.TaskService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TasksResource {
    private final TaskService tasksService;

    @GetMapping("/by-investigation")
    public List<TaskWithProgress> getTasksWithProgress(
            @RequestParam String investigationId,
            @RequestParam String userId) {
        return tasksService.getTasksWithProgress(investigationId, userId);
    }

    
}
