package com.example.survey_app.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    // Default constructor
    public Answer() {
    }

    // Constructor with arguments
    public Answer(String text) {
        this.text = text;
    }

    // Getters and Setters
}
