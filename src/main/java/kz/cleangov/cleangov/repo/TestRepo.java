package kz.cleangov.cleangov.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kz.cleangov.cleangov.domain.Test;

@Repository
public interface TestRepo extends JpaRepository<Test, Long> {}