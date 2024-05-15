package com.example.quiz_mircroservice.quiz_service.model;

import lombok.Data;

@Data
public class QuizDto {

    String categoryName;
    Integer numOfQuestions;
    String title;
}
