package com.examportal.examportalapi.domain;

import com.examportal.examportalapi.data.transfer.object.CategoryDto;
import com.examportal.examportalapi.data.transfer.object.QuestionDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class QuizDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quizId;
    private Boolean activeStatus;
    private String title;
    private String description;
    private String maxMarks;
    private String numberOfQuestions;

    @ManyToOne(fetch = FetchType.EAGER)
    private CategoryDomain category;


    @OneToMany(mappedBy = "quiz" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<QuestionDomain> questions = new HashSet<>();

}