package com.survey.demo.models.surveys;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.survey.demo.models.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Document(collection = "category")
public class Category {

    @Transient
    public static String SEQUENCE_NAME = "category_sequence";
    @Id
    private int cid;
    private String title;
    private String description;

    @DBRef
    @JsonIgnore
    private Set<Survey> surveys = new LinkedHashSet<>();

    public Category(int cid, String title, String description) {
        this.cid = cid;
        this.title = title;
        this.description = description;
    }

    public Category(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
