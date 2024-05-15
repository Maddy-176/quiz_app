package com.example.quiz_mircroservice.quiz_service.feign;

import com.example.quiz_mircroservice.quiz_service.model.QuestionDto;
import com.example.quiz_mircroservice.quiz_service.model.Responses;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@FeignClient("QUESTION_SERVICE")
public interface QuizInterface {
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuizQuestions
            (@RequestParam String categoryName, @RequestParam Integer numOfQues);

    @PostMapping("question/getQuizQues")
    public ResponseEntity<List<QuestionDto>> getQuestionsFromId(@RequestBody List<Integer>quesIds);

    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore( @RequestBody List<Responses> responses);

}
