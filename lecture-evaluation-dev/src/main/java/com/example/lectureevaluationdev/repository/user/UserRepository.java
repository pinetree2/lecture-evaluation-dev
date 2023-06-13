package com.example.lectureevaluationdev.repository.user;

import com.example.lectureevaluationdev.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserID(String userID);
}