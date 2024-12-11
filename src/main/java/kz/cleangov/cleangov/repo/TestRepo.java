package kz.cleangov.cleangov.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kz.cleangov.cleangov.domain.Test;

@Repository
public interface TestRepo extends JpaRepository<Test, Long> {
    List<Test> findByTaskId(String taskId); 

    Optional<Test> findById(Long testId);
}