package kz.cleangov.cleangov.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kz.cleangov.cleangov.domain.Progress;
import kz.cleangov.cleangov.domain.Tasks;
import kz.cleangov.cleangov.domain.Test;
import kz.cleangov.cleangov.domain.Users;
import kz.cleangov.cleangov.domain.constructorClass.TaskWithProgress;
import kz.cleangov.cleangov.repo.ProgressRepo;
import kz.cleangov.cleangov.repo.TaskRepo;
import kz.cleangov.cleangov.repo.TestRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepo tasksRepo;
    private final TestRepo testRepo;
    private final ProgressRepo progressRepo;

    public List<TaskWithProgress> getTasksWithProgress(String investigationId, String userId) {
        List<Tasks> tasks = tasksRepo.findByInvestigationId(investigationId);
        List<Progress> progressList = progressRepo.findByTaskInvestigationIdAndUserId(investigationId, userId);

        //check
        Map<String, Progress> progressMap = progressList.stream()
                .collect(Collectors.toMap(
                        progress -> progress.getTask().getId(),
                        progress -> progress
                ));

        //new progress
        List<Progress> newProgressList = tasks.stream()
                .filter(task -> !progressMap.containsKey(task.getId()))
                .map(task -> {
                    Progress newProgress = new Progress();
                    newProgress.setTask(task);
                    newProgress.setUser(new Users(userId));
                    newProgress.setProgress(0);
                    newProgress.setCompleted(false);
                    return newProgress;
                })
                .collect(Collectors.toList());
        if (!newProgressList.isEmpty()) {
            progressRepo.saveAll(newProgressList);
        }

        progressMap.putAll(
                newProgressList.stream()
                        .collect(Collectors.toMap(
                                progress -> progress.getTask().getId(),
                                progress -> progress
                        ))
        );
        
        return tasks.stream()
                .map(task -> {
                    Progress progress = progressMap.get(task.getId());
                    int progressValue = progress != null ? progress.getProgress() : 0;
                    return new TaskWithProgress(task, progressValue);
                })
                .collect(Collectors.toList());
    }

    public String getTaskIdByTestId(Long testId) {
        Test test = testRepo.findById(testId)
                .orElseThrow(() -> new IllegalArgumentException("Test not found"));
        return test.getTask().getId(); // Предполагаем, что `task` связан с `Test`
    }
}
