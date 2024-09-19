package com.example.survey_app.service;

import com.example.survey_app.models.SurveyResponse;

import java.util.List;

public interface SurveyResponseService {
    void saveResponse(SurveyResponse response);

    List<SurveyResponse> getResponsesBySurveyId(Long surveyId);
}
