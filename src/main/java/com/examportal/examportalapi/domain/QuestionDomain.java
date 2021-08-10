package com.examportal.examportalapi.domain;


import lombok.*;


import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class QuestionDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;
    private String answer;
    private String content;
    private String image;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    @ManyToOne(fetch = FetchType.EAGER)
    private QuizDomain quiz;
}