package com.example.survey_app.models;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;


@Getter
@Entity
@Data
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private boolean published;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_user_id")
    private User user;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Question> questions;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<SurveyResponse> responses;

    // Default constructor
    public Survey() {
    }

    // Constructor with arguments
    public Survey(String title, String description) {
        this.title = title;
        this.description = description;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setResponses(List<SurveyResponse> responses) {
        this.responses = responses;
    }
}
