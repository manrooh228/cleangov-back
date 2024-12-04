package kz.cleangov.cleangov.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kz.cleangov.cleangov.domain.Investigations;
import kz.cleangov.cleangov.service.InvestigationsService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/investigations")
@RequiredArgsConstructor
public class InvestigationResource {
    private final InvestigationsService investigationService;

    // Получить все дела
    @GetMapping("/all")
    public List<Investigations> getAllInvestigations() {
        return investigationService.getAllInvestigations();
    }

    // Получить дела с прогрессом для пользователя
    @GetMapping("/progress")
    public List<Investigations> getTasksWithProgress(@RequestParam String userId) {
        return investigationService.getTasksWithProgress(userId);
    }

    @GetMapping("/all-with-progress")
    public List<Investigations> getInvestigationsWithProgress(@RequestParam String userId) {
        return investigationService.getInvestigationsWithUserProgress(userId);
    }

}
