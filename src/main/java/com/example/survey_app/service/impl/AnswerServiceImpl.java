package com.example.survey_app.service.impl;


import com.example.survey_app.models.Answer;
import com.example.survey_app.repository.AnswerRepository;
import com.example.survey_app.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    @Override
    public Answer findById(Long id) {
        return answerRepository.findById(id).orElse(null);
    }

    @Override
    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public void deleteById(Long id) {
        answerRepository.deleteById(id);
    }
}