package kz.cleangov.cleangov.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kz.cleangov.cleangov.domain.Tasks;

@Repository
public interface TaskRepo extends JpaRepository<Tasks, String>{
    List<Tasks> findByInvestigationId(String investigationId);
}