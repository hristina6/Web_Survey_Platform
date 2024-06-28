package com.example.survey_app.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
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

    // Getters and Setters
}
