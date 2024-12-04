package kz.cleangov.cleangov.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kz.cleangov.cleangov.domain.Investigations;
import kz.cleangov.cleangov.domain.Progress;
import kz.cleangov.cleangov.domain.ProgressInvest;
import kz.cleangov.cleangov.repo.InvestigationsRepo;
import kz.cleangov.cleangov.repo.ProgressInvestRepo;
import kz.cleangov.cleangov.repo.ProgressRepo;
import kz.cleangov.cleangov.resource.InvestigationWithProgress;
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

    // Получить расследования с прогрессом пользователя
    public List<InvestigationWithProgress> getInvestigationsWithUserProgress(String userId) {
        // Получаем все расследования
        List<Investigations> allInvestigations = investigationsRepository.findAll();

        // Получаем прогресс для пользователя
        List<ProgressInvest> progressList = progressInvestRepo.findByUserId(userId);

        // Создаем карту для быстрого поиска прогресса по Investigation ID
        Map<String, Integer> progressMap = progressList.stream()
                .collect(Collectors.toMap(
                        progress -> progress.getInvestigation().getId(),
                        ProgressInvest::getProgress
                ));

        // Преобразуем в список объектов, которые будут включать и расследования, и прогресс
        return allInvestigations.stream()
                .map(investigation -> {
                    int progress = progressMap.getOrDefault(investigation.getId(), 0); // Прогресс или 0
                    return new InvestigationWithProgress(investigation, progress); // Создаем новый объект с прогрессом
                })
                .collect(Collectors.toList());
    }

}