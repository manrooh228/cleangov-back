package kz.cleangov.cleangov.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kz.cleangov.cleangov.domain.Answers;
import kz.cleangov.cleangov.domain.Question;
import kz.cleangov.cleangov.domain.Test;
import kz.cleangov.cleangov.domain.TestResult;
import kz.cleangov.cleangov.service.TestService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tests")
@RequiredArgsConstructor
public class TestResource {
    private final TestService testService;

    @GetMapping("/by-task")
    public List<Test> getTestsByTask(@RequestParam Long taskId) {
        return testService.getTestsByTask(taskId);
    }

    @GetMapping("/{testId}/questions")
    public List<Question> getQuestionsByTest(@PathVariable Long testId) {
        return testService.getQuestionsByTest(testId);
    }

    @GetMapping("/{questionId}/answers")
    public List<Answers> getAnswersByQuestion(@PathVariable Long questionId) {
        return testService.getAnswersByQuestion(questionId);
    }

    @PostMapping("/save-result")
    public void saveTestResult(@RequestBody TestResult result) {
        testService.saveTestResult(result);
    }
}
