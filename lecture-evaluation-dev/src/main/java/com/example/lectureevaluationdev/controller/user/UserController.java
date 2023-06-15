package com.example.lectureevaluationdev.controller.user;

import com.example.lectureevaluationdev.primary.LoginResponse;
import com.example.lectureevaluationdev.entity.user.User;
import com.example.lectureevaluationdev.primary.Role;
import com.example.lectureevaluationdev.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join")
    @ResponseBody
    public LoginResponse signUp(@RequestBody Map<String,Object> user) throws Exception{
        //Role role = Role.from(user.get("role").toString()); 굳이 유저 역할 부여안해도됨
        User userInfo = User.builder()
                .userID(user.get("userID").toString())
                .userPassword(user.get("userPassword").toString())
                .userEmail(user.get("userEmail").toString())
                .build();
        LoginResponse result = userService.signUpUser(userInfo);
        return result;
    }



    @PostMapping("/login")
    @ResponseBody
    public LoginResponse login(HttpServletRequest request,@RequestBody Map<String, String> user) throws Exception{
       User getuser = User.builder()
               .userID(user.get("userID").toString())
               .userPassword(user.get("userPassword").toString())
               .userEmail(user.get("userEmail").toString())
               .build();
       /*
        JSONParser parser = new JSONParser();
        Gson gson = new Gson();
*/

        return this.userService.login(getuser);
    }



}
