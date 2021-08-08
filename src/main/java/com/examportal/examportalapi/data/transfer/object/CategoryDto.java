package com.examportal.examportalapi.data.transfer.object;

import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDto {

    private int categoryId;
    private String title;
    private String description;

    private Set<QuizDto> quizDtos = new LinkedHashSet<>();
}
