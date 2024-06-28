package com.example.survey_app.service;

import com.example.survey_app.models.Survey;
import com.example.survey_app.models.SurveyResponse;

import java.util.List;

public interface SurveyService {
    void createSurvey(Survey survey);
    void publishSurvey(Long surveyId);
    List<Survey> getAllSurveys();
    Survey getSurveyById(Long id);
    void saveResponse(SurveyResponse response);
    List<SurveyResponse> getSurveyResponses(Long surveyId);
}