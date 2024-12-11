package kz.cleangov.cleangov.service;

import org.springframework.stereotype.Service;

import kz.cleangov.cleangov.domain.TestResult;
import kz.cleangov.cleangov.repo.TestResultRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestResultService {
    final TestResultRepo testResultRepo;

    public int getTestResult(Long testId, String userId) {
        TestResult testResult = testResultRepo.findByTestIdAndUserId(testId, userId);
        if (testResult != null) {
            return testResult.getSuccessPercentage();
        }
        return -1;
    }
}
