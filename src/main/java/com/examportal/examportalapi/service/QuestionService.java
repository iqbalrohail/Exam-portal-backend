package com.examportal.examportalapi.service;

import com.examportal.examportalapi.data.transfer.object.QuestionDto;
import com.examportal.examportalapi.data.transfer.object.MessageDto;
import com.examportal.examportalapi.domain.QuestionDomain;
import com.examportal.examportalapi.domain.QuizDomain;
import com.examportal.examportalapi.repository.QuestionRepository;
import com.examportal.examportalapi.repository.QuizRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class QuestionService {

    private final Logger log = LoggerFactory.getLogger(QuestionService.class);

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private QuizRepository quizRepository;

    public MessageDto addQuestion(QuestionDto questionDto)
    {
        QuestionDomain questionDomain = mapDtoToDomain(questionDto);
        questionRepository.save(questionDomain);
        log.info("Question have been saved with id "+questionDomain.getQuestionId());
        String message = "Question have been saved with id "+questionDomain.getQuestionId();
        MessageDto messageDto = new MessageDto(message);
        return messageDto;
    }

    public QuestionDto getQuestionById(int id)
    {
        QuestionDomain questionDomain = questionRepository.findById(id).get();
        return mapDomainToDto(questionDomain);
    }

    public MessageDto updateQuestionById(QuestionDto questionDto , int id)
    {
        if(questionRepository.findById(id).isPresent())
        {
            questionDto.setQuestionId(id);
            questionRepository.save(mapDtoToDomain(questionDto));
            String message = "Question have been Updated with id "+id;
            MessageDto messageDto = new MessageDto(message);
            return messageDto;
        }
        else {
            String message = "Question not found with id "+id;
            MessageDto messageDto = new MessageDto(message);
            return messageDto;
        }
    }
    public MessageDto deleteQuestionById(int id)
    {
        if(questionRepository.findById(id).isPresent())
        {
            questionRepository.deleteById(id);
            String message = "Question have been deleted with id "+id;
            MessageDto messageDto = new MessageDto(message);
            return messageDto;
        }
        else {
            String message = "Question not found with id "+id;
            MessageDto messageDto = new MessageDto(message);
            return messageDto;
        }

    }

    public List<QuestionDomain> getQuestionsOfQuiz(int id)
    {
        QuizDomain quizDomain =quizRepository.findById(id).get();
        Set<QuestionDomain> qustions =  quizDomain.getQuestions();
        List list = new ArrayList(qustions);
        if(list.size() > Integer.parseInt(quizDomain.getNumberOfQuestions()))
        {
            list = list.subList(0 , Integer.parseInt(quizDomain.getNumberOfQuestions())+1);

        }
        Collections.shuffle(list);
        return list;
    }

    public QuestionDomain mapDtoToDomain(QuestionDto questionDto) {
        return objectMapper.convertValue(questionDto, QuestionDomain.class);
    }

    public QuestionDto mapDomainToDto(QuestionDomain questionDomain) {
        return objectMapper.convertValue(questionDomain, QuestionDto.class);
    }

}
