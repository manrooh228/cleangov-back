package kz.cleangov.cleangov.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kz.cleangov.cleangov.domain.Answers;
import kz.cleangov.cleangov.domain.Question;
import kz.cleangov.cleangov.domain.Test;
import kz.cleangov.cleangov.domain.TestResult;
import kz.cleangov.cleangov.repo.AnswerRepo;
import kz.cleangov.cleangov.repo.QuestionRepo;
import kz.cleangov.cleangov.repo.TestRepo;
import kz.cleangov.cleangov.repo.TestResultRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepo testRepo;
    private final QuestionRepo questionRepo;
    private final AnswerRepo answerRepo;
    private final TestResultRepo testResultRepo;

    public List<Test> getTestsByTask(Long taskId) {
        return testRepo.findAll().stream()
                .filter(test -> test.getTask().getId().equals(taskId))
                .collect(Collectors.toList());
    }

    public List<Question> getQuestionsByTest(Long testId) {
        return questionRepo.findAll().stream()
                .filter(question -> question.getTest().getId().equals(testId))
                .collect(Collectors.toList());
    }

    public List<Answers> getAnswersByQuestion(Long questionId) {
        return answerRepo.findAll().stream()
                .filter(answer -> answer.getQuestion().getId().equals(questionId))
                .collect(Collectors.toList());
    }

    public void saveTestResult(TestResult result) {
        testResultRepo.save(result);
    }
}
