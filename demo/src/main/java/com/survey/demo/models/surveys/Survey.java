package com.survey.demo.models.surveys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Document(collection="survey")
public class Survey {

    @Id
    private String sId;
    private String title;

    private String description;

    private String maxMarks;

    private String numberOfQuestions;

    private boolean active = false;


   //@ManyToOne(fetch = fetchType.EAGER)


    @DBRef // Use DBRef to create a reference to the Category document
    private Category category;
    //@OneToMany(mappedBy = "survey",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    //@DBRef
    private Set<Question> questions = new HashSet<>();

}
