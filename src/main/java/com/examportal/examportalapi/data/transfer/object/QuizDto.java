package com.examportal.examportalapi.data.transfer.object;

import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuizDto {
    private int quizId;
    private Boolean activeStatus;
    private String title;
    private String description;
    private String maxMarks;
    private String numberOfQuestions;

    private CategoryDto category;
    private Set<QuestionDto> questions = new HashSet<>();
}
