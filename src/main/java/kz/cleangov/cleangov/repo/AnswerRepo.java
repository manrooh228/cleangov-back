package kz.cleangov.cleangov.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kz.cleangov.cleangov.domain.Answers;

@Repository
public interface AnswerRepo extends JpaRepository<Answers, Long> {}
