package com.examportal.examportalapi.controller;

import com.examportal.examportalapi.data.transfer.object.QuizDto;
import com.examportal.examportalapi.data.transfer.object.MessageDto;
import com.examportal.examportalapi.domain.QuizDomain;
import com.examportal.examportalapi.service.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("quiz")
public class QuizController {

    private final Logger log = LoggerFactory.getLogger(QuizController.class);

    @Autowired
    private QuizService quizService;

    @PostMapping("/add")
    public MessageDto addQuiz(@RequestBody QuizDto quizDto)
    {
        log.info("Post call have been received at quiz/add with DTO "+quizDto);
        return quizService.addQuiz(quizDto);
    }

    @GetMapping("/get/{quizId}")
    public QuizDto getQuizById(@PathVariable("quizId") int quizId)
    {
        return quizService.getQuizById(quizId);
    }

    @GetMapping("/getAll")
    public Set<QuizDomain> getAllQuizes()
    {
        return quizService.getAllQuizes();
    }

    @PutMapping("/update/{quizId}")
    public MessageDto getQuizById(@RequestBody QuizDto quizDto , @PathVariable("quizId") int quizId)
    {
        return quizService.updateQuizById(quizDto , quizId);
    }

    @DeleteMapping("/delete/{id}")
    public MessageDto deleteQuizById(@PathVariable("id") int id)
    {
        return quizService.deleteQuizById(id);

    }


}
