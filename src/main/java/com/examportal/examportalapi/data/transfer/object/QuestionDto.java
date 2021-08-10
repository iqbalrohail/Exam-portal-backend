package com.examportal.examportalapi.data.transfer.object;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionDto {
    private int questionId;
    private String answer;
    private String content;
    private String image;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    private QuizDto quiz;
}
