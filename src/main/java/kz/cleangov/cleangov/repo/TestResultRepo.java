package kz.cleangov.cleangov.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kz.cleangov.cleangov.domain.TestResult;

@Repository
public interface TestResultRepo extends JpaRepository<TestResult, Long> {}