package com.example.lectureevaluationdev.repository.evaluation;

import com.example.lectureevaluationdev.entity.evaluation.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    Optional<Evaluation> findByEvaluationID(long evaluationID);
}
