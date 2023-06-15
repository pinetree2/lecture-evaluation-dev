package com.example.lectureevaluationdev.entity.evaluation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data //get,set 메소드 이용가능하게 하는 어노테이션
@Table(name = "evaluation")
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//는 JPA에서 기본 키를 자동으로 생성할 때 사용하는 방법 중 하나
    @Column(name="evaluationID")
    int evaluationID;

    @Column(name="user_ID")
    String userID;

    String lectureName;

    String professorName;

    int lectureYear;

    String semesterDivide;

    String lectureDivide;

    String evaluationTitle;

    String evaluationContent;

    String totalScore;

    String creditScore;

    String comfortableScore;

    String lectureScore;

    int likeCount;

}
