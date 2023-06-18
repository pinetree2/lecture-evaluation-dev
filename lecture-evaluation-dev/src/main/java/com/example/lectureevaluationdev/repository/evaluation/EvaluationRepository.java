package com.example.lectureevaluationdev.repository.evaluation;

import com.example.lectureevaluationdev.entity.evaluation.Evaluation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long>, PagingAndSortingRepository<Evaluation,Long> {
    //Optional<Evaluation> findByEvaluationID(long evaluationID);
    Evaluation findByEvaluationID(long evaluationID);
    List<Evaluation> findBySearchLike(String search);
    Page<Evaluation> findAll(Pageable pageable);
    List<Evaluation> Sort(Sort sort);
    Page<Evaluation> findByContentContainingIgnoreCase(String search, Pageable pageable);
    Page<Evaluation> findByLectureDivideAndContentContainingIgnoreCase(String lectureDivide, String search, Pageable pageable);
}
