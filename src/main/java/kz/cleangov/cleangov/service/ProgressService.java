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
    private final ProgressInvestRepo progressInvestRepository;
    private final ProgressRepo progressRepo;
    private final UserRepo userRepo;
    private final TaskRepo taskRepo;


    public void handleCompletedProgress(Progress progress) {
        if (progress.isCompleted()) {
            ProgressInvest progressInvest = progressInvestRepository
                .findByUserIdAndInvestigationId(
                    progress.getUser().getId(),
                    progress.getTask().getInvestigation().getId()
                );

            if (progressInvest != null) {
                int newProgress = Math.min(100, progressInvest.getProgress() + 25); // Не больше 100%
                progressInvest.setProgress(newProgress);
                progressInvest.setCompleted(newProgress == 100);
                progressInvestRepository.save(progressInvest);
            }
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
