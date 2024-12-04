package kz.cleangov.cleangov.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kz.cleangov.cleangov.domain.ProgressInvest;

@Repository
public interface ProgressInvestRepo extends JpaRepository<ProgressInvest, String> {
    ProgressInvest findByUserIdAndInvestigationId(String userId, String investigationId);
}
