package com.example.survey_app.service;

import com.example.survey_app.models.*;

import java.util.List;

public interface SurveyService {
    void createSurvey(Survey survey);
    void publishSurvey(Long surveyId);
    List<Survey> getAllSurveys();
    Survey getSurveyById(Long id);
    void saveResponse(SurveyResponse response);
    List<SurveyResponse> getSurveyResponses(Long surveyId);

    void deleteSurvey(Long id);

    void unpublishSurvey(Long id);

    void updateSurvey(Long id, Survey survey);

    public Question getQuestionById(Long id);

    public Answer getAnswerById(Long id);

    List<Survey> getSurveysByUser(User user);
}