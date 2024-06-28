package com.example.survey_app.service;

import com.example.survey_app.models.Question;

import java.util.List;

public interface QuestionService {
    List<Question> findAll();
    Question findById(Long id);
    Question save(Question question);
    void deleteById(Long id);
}