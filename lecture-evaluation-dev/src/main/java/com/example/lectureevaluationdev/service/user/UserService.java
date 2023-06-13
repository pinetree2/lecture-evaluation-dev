package com.example.lectureevaluationdev.service.user;

import com.example.lectureevaluationdev.primary.LoginResponse;
import com.example.lectureevaluationdev.primary.ResponseService;
import com.example.lectureevaluationdev.entity.user.User;
import com.example.lectureevaluationdev.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService extends ResponseService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse login(User user) {
        User foundUser = userRepository.findByUserID(user.getUserID());
        int result = 0;
        boolean success = result == 1;

        try {
            if (user != null && foundUser.getUserPassword().equals(user.getUserPassword())) {
                result = 1; // 로그인 성공
                return setResponse(200, "message", "로그인 성공");
            } else if (foundUser == null) {
                result = -1; // ID 없음
                return setResponse(404, "message", "존재하지 않는 ID입니다.");

            } else {
                result = 0; //로그인 실패
                return setResponse(402, "message", "로그인 실패");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public LoginResponse signupUser(User userInfo) {
        try {

            userRepository.save(userInfo);//유저정보 저장 메소드 (insert문을 쓰지않아도..된다니!)
            return setResponse(200, "message", "회원가입 완료");

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }


    //참고
    private boolean isValidEmail(String email) {
        boolean err = false;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if (m.matches()) {
            err = true;
        }
        return err;
    }


}