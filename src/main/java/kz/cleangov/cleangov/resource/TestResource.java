package kz.cleangov.cleangov.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import kz.cleangov.cleangov.domain.constructorClass.QuestionDTO;
import kz.cleangov.cleangov.service.TestService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tests")
@RequiredArgsConstructor
public class TestResource {
    private final TestService testService;

    @GetMapping("/task/{taskId}")
    public List<Test> getTestsByTask(@PathVariable String taskId) {
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

    @GetMapping("/{testId}/questions-with-answers")
    public ResponseEntity<List<QuestionDTO>> getQuestionsWithAnswers(@PathVariable Long testId) {
        List<QuestionDTO> questions = testService.getQuestionsWithAnswersByTestId(testId);
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{testId}")
    public ResponseEntity<Test> getTestById(@PathVariable Long testId) {
        try {
            Test test = testService.getTestById(testId);  // Получаем тест по ID
            return ResponseEntity.ok(test);  // Возвращаем успешный ответ с тестом
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null);  // Если тест не найден, возвращаем 404
        }
    }
}
