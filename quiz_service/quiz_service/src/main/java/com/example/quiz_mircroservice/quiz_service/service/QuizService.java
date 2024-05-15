package com.example.quiz_mircroservice.quiz_service.service;

import com.example.quiz_mircroservice.quiz_service.dao.QuizDao;
import com.example.quiz_mircroservice.quiz_service.model.QuestionDto;
import com.example.quiz_mircroservice.quiz_service.model.Quiz;
import com.example.quiz_mircroservice.quiz_service.feign.QuizInterface;
import com.example.quiz_mircroservice.quiz_service.model.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;


    public ResponseEntity<String > createQuiz(String category, int numQ, String quizTitle) {
        List<Integer> qustions=quizInterface.getQuizQuestions(category,numQ).getBody();
        Quiz quiz= new Quiz();
        quiz.setTitle(quizTitle);
        quiz.setQuestions(qustions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionDto>> getQuizQuestions(int id){
        Optional<Quiz> quiz= quizDao.findById(id);
//        List<Question> questionsFromDb= quiz.get().getQuestions();
        List<QuestionDto> quesForUser= new ArrayList<>();
//        for(Question q: questionsFromDb){
//            QuestionDto dto= new QuestionDto(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption2(),q.getOption4());
//            quesForUser.add(dto);
//        }
        return new ResponseEntity<>(quesForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Responses> response) {
        Quiz quiz= quizDao.findById(id).get();
//        List<Question> questions= quiz.getQuestions();
//        Map<Integer,String> resMap= new HashMap<>();
//        for(Responses res:response){
//            resMap.put(res.getId(),res.getResponse());
//        }
        int score=0;
//        for(Question ques:questions){
//            int quesId=ques.getId();
//            String correctAns= ques.getRightAnswer();
//            if(resMap.containsKey(quesId)){
//                if(correctAns.equals(resMap.get(quesId))){
//                    score+=1;
//                }
//            }
//        }
        return new ResponseEntity<Integer>(score,HttpStatus.OK);
    }
}
