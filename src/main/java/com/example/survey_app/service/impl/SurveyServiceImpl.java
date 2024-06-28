package com.example.survey_app.service.impl;

import com.example.survey_app.models.Survey;
import com.example.survey_app.models.SurveyResponse;
import com.example.survey_app.repository.SurveyRepository;
import com.example.survey_app.repository.SurveyResponseRepository;
import com.example.survey_app.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyResponseRepository surveyResponseRepository;

    @Override
    public void createSurvey(Survey survey) {
        // Initialize answers list for each question
        survey.getQuestions().forEach(question -> {
            if (question.getAnswers() == null) {
                question.setAnswers(new ArrayList<>());
            }
        });

        surveyRepository.save(survey);
    }

    @Override
    public void publishSurvey(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId).orElseThrow(InvalidParameterException::new);
        survey.setPublished(true);
        surveyRepository.save(survey);
    }

    @Override
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public Survey getSurveyById(Long id) {
        return surveyRepository.findById(id).orElseThrow(InvalidParameterException::new);
    }

    @Override
    public void saveResponse(SurveyResponse response) {
        surveyResponseRepository.save(response);
    }

    @Override
    public List<SurveyResponse> getSurveyResponses(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId).orElseThrow(InvalidParameterException::new);
        return survey.getResponses();
    }
}
