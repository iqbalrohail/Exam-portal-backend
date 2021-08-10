package com.examportal.examportalapi.service;

import com.examportal.examportalapi.data.transfer.object.QuizDto;
import com.examportal.examportalapi.data.transfer.object.MessageDto;
import com.examportal.examportalapi.domain.QuizDomain;
import com.examportal.examportalapi.repository.QuizRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class QuizService {

    private final Logger log = LoggerFactory.getLogger(QuizService.class);

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public MessageDto addQuiz(QuizDto QuizDto)
    {
        QuizDomain quizDomain = mapDtoToDomain(QuizDto);
        quizRepository.save(quizDomain);
        log.info("Quiz have been saved with id "+quizDomain.getQuizId());
        String message = "Quiz have been saved with id "+quizDomain.getQuizId();
        MessageDto messageDto = new MessageDto(message);
        return messageDto;
    }

    public QuizDto getQuizById(int id)
    {
        QuizDomain QuizDomain = quizRepository.findById(id).get();
        return mapDomainToDto(QuizDomain);
    }

    public MessageDto updateQuizById(QuizDto QuizDto , int id)
    {
        if(quizRepository.findById(id).isPresent())
        {
            QuizDto.setQuizId(id);
            quizRepository.save(mapDtoToDomain(QuizDto));
            String message = "Quiz have been Updated with id "+id;
            MessageDto messageDto = new MessageDto(message);
            return messageDto;
        }
        else {
            String message = "Quiz not found with id "+id;
            MessageDto messageDto = new MessageDto(message);
            return messageDto;
        }
    }

    public MessageDto deleteQuizById(int id)
    {
        if(quizRepository.findById(id).isPresent())
        {
         QuizDomain quizDomain = new QuizDomain();
         quizDomain.setQuizId(id);
         quizRepository.delete(quizDomain);
            String message = "Quiz have been deleted with id "+id;
            MessageDto messageDto = new MessageDto(message);
            return messageDto;
        }
        else {
            String message = "Quiz not found with id "+id;
            MessageDto messageDto = new MessageDto(message);
            return messageDto;
        }

    }

    public Set<QuizDomain> getAllQuizes()
    {
        return new LinkedHashSet<>(quizRepository.findAll());
    }

    public QuizDomain mapDtoToDomain(QuizDto QuizDto) {
        return objectMapper.convertValue(QuizDto, QuizDomain.class);
    }

    public QuizDto mapDomainToDto(QuizDomain QuizDomain) {
        return objectMapper.convertValue(QuizDomain, QuizDto.class);
    }

}
