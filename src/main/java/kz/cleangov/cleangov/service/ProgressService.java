package kz.cleangov.cleangov.service;

import org.springframework.stereotype.Service;

import kz.cleangov.cleangov.domain.Progress;
import kz.cleangov.cleangov.domain.ProgressInvest;
import kz.cleangov.cleangov.domain.Tasks;
import kz.cleangov.cleangov.domain.Users;
import kz.cleangov.cleangov.repo.ProgressInvestRepo;
import kz.cleangov.cleangov.repo.ProgressRepo;
import kz.cleangov.cleangov.repo.TaskRepo;
import kz.cleangov.cleangov.repo.UserRepo;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ProgressService {
    private final AchievementService achievementService;
    private final ProgressInvestRepo progressInvestRepository;
    private final ProgressRepo progressRepo;
    private final UserRepo userRepo;
    private final TaskRepo taskRepo;


    public void handleCompletedProgress(Progress progress) {
        if (progress.isCompleted()) {
            Tasks task = progress.getTask();
            String taskType = task.getTasktype();
            int progressIncrement = getProgressIncrement(taskType);
    
            ProgressInvest progressInvest = progressInvestRepository
                .findByUserIdAndInvestigationId(
                    progress.getUser().getId(),
                    task.getInvestigation().getId()
                );
    
            if (progressInvest == null) {
                progressInvest = new ProgressInvest();
                progressInvest.setUser(progress.getUser());
                progressInvest.setInvestigation(task.getInvestigation());
                progressInvest.setProgress(0);
                progressInvest.setCompleted(false);
            }
    
            progressInvest.addProgress(progressIncrement);
            progressInvestRepository.save(progressInvest);
    
            // Проверка на прогресс 100%
            if (progressInvest.getProgress() >= 100 && !progressInvest.isCompleted()) {
                progressInvest.setCompleted(true);
                progressInvestRepository.save(progressInvest);
    
                // Проверка и выдача ачивки
            }

            if (progressInvest.getProgress() == 100) {
                achievementService.checkAndAwardAchievement(progress.getUser(), "The young investigator");
            }
        }
    }
    

    private int getProgressIncrement(String taskType) {
        // Определяем, сколько процентов добавлять в зависимости от типа задания
        switch (taskType) {
            case "video":
                return 20;
            case "test":
                return 60;
            case "reflexio":
                return 20;
            default:
                throw new IllegalArgumentException("Unknown task type: " + taskType);
        }
    }

    public void updateProgress(String userId, String taskId, int progress) {
        Progress progressEntry = progressRepo.findByUserIdAndTaskId(userId, taskId);

        if (progressEntry != null) {
            progressEntry.setProgress(progress, this); // Обновляем прогресс
            progressRepo.save(progressEntry); // Сохраняем изменения
        } else {
            // Если записи нет, создаём новую
            Users user = userRepo.findById(userId).orElseThrow();
            Tasks task = taskRepo.findById(taskId).orElseThrow();
            Progress newProgress = new Progress();
            newProgress.setUser(user);
            newProgress.setTask(task);
            newProgress.setProgress(progress, this); // Устанавливаем прогресс
            progressRepo.save(newProgress);
        }
    }
}
