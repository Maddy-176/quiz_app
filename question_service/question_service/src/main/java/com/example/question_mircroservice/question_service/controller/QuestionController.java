package com.example.question_mircroservice.question_service.controller;

import com.example.question_mircroservice.question_service.dto.QuestionDto;
import com.example.question_mircroservice.question_service.model.Question;
import com.example.question_mircroservice.question_service.model.Responses;
import com.example.question_mircroservice.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public  ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuizQuestions
    (@RequestParam String categoryName, @RequestParam Integer numOfQues){
        return questionService.getQuestionForQuiz(categoryName,numOfQues);
    }

    @PostMapping("getQuizQues")
    public ResponseEntity<List<QuestionDto>> getQuestionsFromId(@RequestBody List<Integer>quesIds){
        return questionService.getQuestionsFromIds(quesIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore( @RequestBody List<Responses> responses){
        return questionService.calculateScore(responses);
    }

}
