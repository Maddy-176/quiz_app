package com.example.quiz_mircroservice.quiz_service.dao;

import com.example.quiz_mircroservice.quiz_service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz,Integer> {
}
