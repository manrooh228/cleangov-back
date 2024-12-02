package kz.cleangov.cleangov.service;

import org.springframework.stereotype.Service;

import kz.cleangov.cleangov.domain.Investigations;
import kz.cleangov.cleangov.domain.Progress;
import kz.cleangov.cleangov.repo.InvestigationsRepo;
import kz.cleangov.cleangov.repo.ProgressRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgressService {
    private final ProgressRepo progressRepository;
    private final InvestigationsRepo investigationsRepository;

    public void updateProgress(String progressId, int progressValue) {
        Progress progress = progressRepository.findById(progressId)
            .orElseThrow(() -> new IllegalArgumentException("Progress not found"));

        // Используем новый метод setProgress
        progress.setProgress(progressValue, this);

        progressRepository.save(progress);
    }

    public void handleCompletedProgress(Progress progress) {
        Investigations investigation = progress.getTask().getInvestigation();
        if (investigation != null) {
            int newProgress = investigation.getProgress() + 25;
            investigation.setProgress(Math.min(newProgress, 100));
            investigationsRepository.save(investigation);
        }
    }
}
