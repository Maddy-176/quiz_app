package com.example.question_mircroservice.question_service.service;

import com.example.question_mircroservice.question_service.dao.QuestionDao;
import com.example.question_mircroservice.question_service.dto.QuestionDto;
import com.example.question_mircroservice.question_service.model.Question;
import com.example.question_mircroservice.question_service.model.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions(){
        try {
            return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(new ArrayList<Question>(), HttpStatus.BAD_REQUEST);
        }
    }

    public  ResponseEntity<List<Question>> getQuestionsByCategory(String category){
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(new ArrayList<Question>(),HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> addQuestion(Question question){
        var res=questionDao.save(question);
        if(res!=null){
            return new ResponseEntity<>("Question added successfully",HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Question adding failed",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String categoryName, Integer numOfQues) {
        List<Integer> res= questionDao.findRandomQuestionsByCategory(categoryName,numOfQues);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionDto>> getQuestionsFromIds(List<Integer> quesIds) {
        List<QuestionDto>quesList= new ArrayList<>();
        for(Integer id: quesIds){
            Question ques= questionDao.findById(id).get();
            quesList.add(new QuestionDto(
                    ques.getId(),
                    ques.getQuestionTitle(),
                    ques.getOption1(),
                    ques.getOption2(),
                    ques.getOption3(),
                    ques.getOption4()
            ));
        }
        return new ResponseEntity<>(quesList,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScore(List<Responses> responses) {
        int score=0;
        for(Responses res:responses){
            int quesId=res.getId();
            Question ques= questionDao.findById(quesId).get();
            String correctAns= ques.getRightAnswer();
            if(correctAns.equals(res.getResponse()))
                score++;
        }
        return new ResponseEntity<Integer>(score,HttpStatus.OK);
    }
}
