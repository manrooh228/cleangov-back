package kz.cleangov.cleangov.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kz.cleangov.cleangov.domain.Investigations;
import kz.cleangov.cleangov.domain.Progress;
import kz.cleangov.cleangov.domain.ProgressInvest;
import kz.cleangov.cleangov.repo.InvestigationsRepo;
import kz.cleangov.cleangov.repo.ProgressInvestRepo;
import kz.cleangov.cleangov.repo.ProgressRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvestigationsService {
    private final InvestigationsRepo investigationsRepository;
    private final ProgressRepo progressRepository;
    private final ProgressInvestRepo progressInvestRepo;

     // Доступные дела (открытые)
    // Получить все дела
    public List<Investigations> getAllInvestigations() {
        return investigationsRepository.findAll();
    }

    // Дела с прогрессом
    public List<Investigations> getTasksWithProgress(String userId) {
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

        // Получить дела с прогрессом пользователя
    public List<Investigations> getInvestigationsWithUserProgress(String userId) {
        // Извлекаем прогресс пользователя
        List<ProgressInvest> progressList = progressInvestRepo.findByUserId(userId);

        // Преобразуем в список уникальных расследований
        return progressList.stream()
                .map(ProgressInvest::getInvestigation) // Берем объект Investigation
                .distinct() // Убираем дубли
                .toList();
    }

}