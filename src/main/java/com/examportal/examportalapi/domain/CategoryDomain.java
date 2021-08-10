package com.examportal.examportalapi.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class CategoryDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    private String title;
    private String description;

    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL , orphanRemoval = true , fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<QuizDomain> quizDomains = new LinkedHashSet<>();
}