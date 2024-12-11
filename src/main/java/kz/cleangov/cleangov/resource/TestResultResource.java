package kz.cleangov.cleangov.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kz.cleangov.cleangov.service.TestResultService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestResultResource {
    final TestResultService testResultService;
    
    @GetMapping("/tests/result/{taskId}")
    public ResponseEntity<Integer> getTestResult(@PathVariable Long taskId, @RequestParam String userId) {
        int result = testResultService.getTestResult(taskId, userId);
        if (result != -1) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }
}
