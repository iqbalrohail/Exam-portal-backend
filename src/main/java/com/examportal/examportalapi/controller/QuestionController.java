package com.examportal.examportalapi.controller;

import com.examportal.examportalapi.data.transfer.object.QuestionDto;
import com.examportal.examportalapi.data.transfer.object.MessageDto;
import com.examportal.examportalapi.domain.QuestionDomain;
import com.examportal.examportalapi.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {


    private final Logger log = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionService questionService;

    @PostMapping("/add")
    public MessageDto addQuestion(@RequestBody QuestionDto questionDto)
    {
        log.info("Post call have been received at question/add with DTO "+questionDto);
        return questionService.addQuestion(questionDto);
    }

    @GetMapping("/quiz/{quizId}")
    public List<QuestionDomain> getQuestionsOfQuiz(@PathVariable("quizId") int quizId)
    {
        log.info("Get call have been received at question/get with id "+quizId);
        return questionService.getQuestionsOfQuiz(quizId);
    }

    @PutMapping("/update/{questionId}")
    public MessageDto getQuestionById(@RequestBody QuestionDto questionDto , @PathVariable("questionId") int questionId)
    {
        log.info("Put call have been received at question/update with id "+questionId);
        return questionService.updateQuestionById(questionDto , questionId);
    }

    @DeleteMapping("/delete/{id}")
    public MessageDto deleteQuestionById(@PathVariable("id") int id)
    {
        log.info("Delete call have been received at question/delete with id "+id);
        return questionService.deleteQuestionById(id);
    }

}
