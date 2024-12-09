package kz.cleangov.cleangov.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kz.cleangov.cleangov.domain.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {}