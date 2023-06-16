package com.example.lectureevaluationdev.service.evaluation;

import com.example.lectureevaluationdev.entity.user.User;
import com.example.lectureevaluationdev.primary.ResponseService;
import com.example.lectureevaluationdev.entity.evaluation.Evaluation;
import com.example.lectureevaluationdev.primary.EvaluationResponse;
import com.example.lectureevaluationdev.repository.evaluation.EvaluationRepository;
import com.example.lectureevaluationdev.repository.user.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
public class EvaluationService extends ResponseService {

    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;

    @Autowired
    public EvaluationService(EvaluationRepository evaluationRepository, UserRepository userRepository) {
        this.evaluationRepository = evaluationRepository;
        this.userRepository = userRepository;
    }


    public EvaluationResponse writeEvaluation(Evaluation evaluationcontent)  {
        //이미 존재하는지 확인을..할수가없네 ? 테이블 구조상..평가 과목별로 id 지정하거나 나눈게 아니라서...
        //또는 나의 작성 게시글 확인같은걸 할수가 없음
        try {
            evaluationRepository.save(evaluationcontent);
            return setResponse(200, "message", "글 작성 성공");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("글 작성 오류 발생");
        }
        return null;

    }

    public EvaluationResponse modifyEvaluation(User userInfo, long evaluationID, Evaluation evaluationcontent) {
        Optional<User> usercheck = Optional.ofNullable(userRepository.findByUserID(userInfo.getUserID()));
        Optional<Evaluation> content = Optional.ofNullable(evaluationRepository.findByEvaluationID(evaluationID));

        try{
            //입력받은 user값과 db의 값이 일치할경우에만
            if(usercheck.isPresent() && content.isPresent()){
                if(usercheck.get().getUserPassword()==userInfo.getUserPassword()){
                    Evaluation evaluations = content.get();

                    if (evaluationcontent.getLectureName() != null) {
                        evaluations.setLectureName(evaluationcontent.getLectureName());
                    }
                    if (evaluationcontent.getProfessorName() != null) {
                        evaluations.setProfessorName(evaluationcontent.getProfessorName());
                    }
                    if (evaluationcontent.getSemesterDivide() != null) {
                        evaluations.setSemesterDivide(evaluationcontent.getSemesterDivide());
                    }
                    if (evaluationcontent.getLectureDivide() != null) {
                        evaluations.setLectureDivide(evaluationcontent.getLectureDivide());
                    }
                    if (evaluationcontent.getEvaluationTitle() != null) {
                        evaluations.setEvaluationTitle(evaluationcontent.getEvaluationTitle());
                    }
                    if (evaluationcontent.getTotalScore() != null) {
                        evaluations.setTotalScore(evaluationcontent.getTotalScore());
                    }
                    if (evaluationcontent.getCreditScore() != null) {
                        evaluations.setCreditScore(evaluationcontent.getCreditScore());
                    }
                    if (evaluationcontent.getComfortableScore() != null) {
                        evaluations.setComfortableScore(evaluationcontent.getComfortableScore());
                    }
                    if (evaluationcontent.getLectureScore() != null) {
                        evaluations.setLectureScore(evaluationcontent.getLectureScore());
                    }
                    //더 간단하게 작성하는 방법을 모르겠다..
                    evaluationRepository.save(evaluations);
                }
                return setResponse(200,"message","수정이 완료되었습니다.");

            }else{
                return setResponse(500,"message","수정이 불가합니다.");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //삭제
    public EvaluationResponse deleteEvaluation(Long evaluationID, User userInfo) {
        Optional<User> usercheck = Optional.ofNullable(userRepository.findByUserID(userInfo.getUserID()));
        Optional<Evaluation> checkevaluation = Optional.ofNullable(evaluationRepository.findByEvaluationID(evaluationID));

        try {
            //입력받은 user값과 db의 값이 일치할경우에만
            if (usercheck.isPresent() && checkevaluation.isPresent()) {
                if (usercheck.get().getUserPassword() == userInfo.getUserPassword()) {
                    evaluationRepository.deleteById(evaluationID);
                    return setResponse(200, "message", "삭제가 완료되었습니다.");
                } else {
                    return setResponse(501, "message", "삭제 실패하였습니다.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
