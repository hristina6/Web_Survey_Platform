package com.example.survey_app.models;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Data
public class ResponseEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "response_id")
    private SurveyResponse surveyResponse;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    private String openText;
}

