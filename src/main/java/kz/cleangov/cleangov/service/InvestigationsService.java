package kz.cleangov.cleangov.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import kz.cleangov.cleangov.domain.Investigations;
import kz.cleangov.cleangov.domain.Progress;
import kz.cleangov.cleangov.repo.InvestigationsRepo;
import kz.cleangov.cleangov.repo.ProgressRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvestigationsService {
    private final InvestigationsRepo investigationsRepository;
    private final ProgressRepo progressRepository;

     // Доступные дела (открытые)
    // Получить все дела
    public List<Investigations> getAllInvestigations() {
        return investigationsRepository.findAll();
    }

    // Дела с прогрессом
    public List<Investigations> getInvestigationsWithProgress(String userId) {
   var progressList = progressRepository.findByUserId(userId);
    return progressList.stream()
            .map(new Function<Progress, Investigations>() {
                @Override
                public Investigations apply(Progress progress) {
                    return progress.getTask().getInvestigation();
                }
            })
            .distinct()
            .toList();
}
}