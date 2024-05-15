package com.example.quiz_mircroservice.quiz_service.controller;

import com.example.quiz_mircroservice.quiz_service.model.QuestionDto;
import com.example.quiz_mircroservice.quiz_service.model.QuizDto;
import com.example.quiz_mircroservice.quiz_service.model.Responses;
import com.example.quiz_mircroservice.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController{
        @Autowired
        QuizService quizService;
        @PostMapping("create")
        public ResponseEntity<String> createQuiz(@RequestParam QuizDto quizDto){
            return new ResponseEntity(quizService.createQuiz(quizDto.getCategoryName(),quizDto.getNumOfQuestions(),quizDto.getTitle()), HttpStatus.OK);
        }

        @GetMapping("get/{quizId}")
        public ResponseEntity<List<QuestionDto>> getQuizQuestions(@PathVariable int quizId){
            return quizService.getQuizQuestions( quizId);
        }

//        @PostMapping("/submit/{id}")
//        public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Responses>response){
//            return quizService.calculateResult(id,response);
//        }
}
