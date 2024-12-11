package kz.cleangov.cleangov.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kz.cleangov.cleangov.domain.Progress;

@Repository
public interface ProgressRepo extends JpaRepository<Progress, String>{
    List<Progress> findByUserId(String userId);
    Progress findByUserIdAndTaskId(String userId, String taskId);
    List<Progress> findByTaskInvestigationIdAndUserId(String investigationId, String userId);
}

