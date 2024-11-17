package kz.cleangov.cleangov.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kz.cleangov.cleangov.domain.Investigations;

@Repository
public interface InvestigationsRepo extends JpaRepository<Investigations, String> {
    @SuppressWarnings("null")
    List<Investigations> findAll();
}

