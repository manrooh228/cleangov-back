package kz.cleangov.cleangov.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kz.cleangov.cleangov.domain.Answers;
import kz.cleangov.cleangov.domain.Question;
import kz.cleangov.cleangov.domain.Test;
import kz.cleangov.cleangov.domain.TestResult;
import kz.cleangov.cleangov.domain.constructorClass.AnswerDTO;
import kz.cleangov.cleangov.domain.constructorClass.QuestionDTO;
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

    public List<Test> getTestsByTask(String taskId) {
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

    public List<Question> getQuestionsWithAnswers(Long testId) {
        return questionRepo.findAll().stream()
                .filter(question -> question.getTest().getId().equals(testId))
                .collect(Collectors.toList());
    }

    
    public void saveTestResult(TestResult result) {
        testResultRepo.save(result);
    }


    public List<QuestionDTO> getQuestionsWithAnswersByTestId(Long testId) {
    List<Question> questions = questionRepo.findByTestId(testId);
    List<QuestionDTO> questionDTOs = new ArrayList<>();
    
    for (Question question : questions) {
        QuestionDTO dto = new QuestionDTO();
        dto.setId(question.getId());
        dto.setTextRu(question.getTextRu());
        
        List<AnswerDTO> answerDTOs = new ArrayList<>();
        for (Answers answer : question.getAnswers()) {
            AnswerDTO answerDTO = new AnswerDTO();
            answerDTO.setId(answer.getId());
            answerDTO.setTextRu(answer.getTextRu());
            answerDTO.setCorrect(answer.isCorrect());
            answerDTOs.add(answerDTO);
        }
        dto.setAnswers(answerDTOs);
        questionDTOs.add(dto);
    }
    
    return questionDTOs;
}

}
