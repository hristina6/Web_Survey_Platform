package com.example.survey_app.service;

import com.example.survey_app.models.Answer;

import java.util.List;

public interface AnswerService {
    List<Answer> findAll();
    Answer findById(Long id);
    Answer save(Answer answer);
    void deleteById(Long id);
}