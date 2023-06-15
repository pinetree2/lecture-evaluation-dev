package com.example.lectureevaluationdev.controller.evaluation;

import com.example.lectureevaluationdev.entity.evaluation.Evaluation;
import com.example.lectureevaluationdev.entity.user.User;
import com.example.lectureevaluationdev.primary.EvaluationResponse;
import com.example.lectureevaluationdev.repository.evaluation.EvaluationRepository;
import com.example.lectureevaluationdev.service.evaluation.EvaluationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/evaluation")
public class EvaluationController {
    private final EvaluationService evaluationService;

    @Autowired
    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    //강의평가 쓰기
    @PostMapping("/write")
    @ResponseBody
    public EvaluationResponse writeEvaluationBoard(HttpServletRequest request, @RequestBody Map<String,Object> content) throws Exception {
        Evaluation evaluationcontent = Evaluation.builder()
                .userID(content.get("userID").toString())
                .lectureName(content.get("lectureName").toString())
                .professorName(content.get("professorName").toString())
                .lectureYear((Integer) content.get("lectureYear"))
                .semesterDivide(content.get("semesterDivide").toString())
                .evaluationTitle(content.get("evaluationTitle").toString())
                .evaluationContent(content.get("evaluationContent").toString())
                .totalScore(content.get("totalScore").toString())
                .creditScore(content.get("creditScore").toString())
                .comfortableScore(content.get("comfortableScore").toString())
                .lectureScore(content.get("lectureScore").toString())
                .build();

        EvaluationResponse result = evaluationService.writeEvaluation(evaluationcontent);
        return result;


    }
    //글 수정
    @PatchMapping("/modify/{evaluationID}")
    @ResponseBody
    public EvaluationResponse modifyEvaluationBoard(HttpServletRequest request,@PathVariable("evaluationID") long evaluationID,@RequestBody Map<String,Object> content ) throws Exception{
        User userInfo = User.builder()
                .userID(content.get("userID").toString())
                .userPassword(content.get("userPassword").toString())
                .userEmail(content.get("userEmail").toString())
                .build();
        Evaluation evaluationcontent = Evaluation.builder()
                .userID(content.get("userID").toString())
                .lectureName(content.get("lectureName").toString())
                .professorName(content.get("professorName").toString())
                .lectureYear((Integer) content.get("lectureYear"))
                .semesterDivide(content.get("semesterDivide").toString())
                .evaluationTitle(content.get("evaluationTitle").toString())
                .evaluationContent(content.get("evaluationContent").toString())
                .totalScore(content.get("totalScore").toString())
                .creditScore(content.get("creditScore").toString())
                .comfortableScore(content.get("comfortableScore").toString())
                .lectureScore(content.get("lectureScore").toString())
                .build();

        EvaluationResponse result = evaluationService.modifyEvaluation(userInfo,evaluationID,evaluationcontent);
        return result;
    }

    //글 삭제

}
