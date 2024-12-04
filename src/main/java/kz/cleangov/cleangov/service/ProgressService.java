package kz.cleangov.cleangov.service;

import org.springframework.stereotype.Service;

import kz.cleangov.cleangov.domain.Progress;
import kz.cleangov.cleangov.domain.ProgressInvest;
import kz.cleangov.cleangov.repo.ProgressInvestRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgressService {
    private final ProgressInvestRepo progressInvestRepository;

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
}
