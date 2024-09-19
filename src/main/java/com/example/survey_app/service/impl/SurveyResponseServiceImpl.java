package com.example.survey_app.service.impl;


import com.example.survey_app.models.SurveyResponse;
import com.example.survey_app.repository.SurveyResponseRepository;
import com.example.survey_app.service.SurveyResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyResponseServiceImpl implements SurveyResponseService {

    @Autowired
    private SurveyResponseRepository surveyResponseRepository;

    @Override
    public void saveResponse(SurveyResponse response) {
        surveyResponseRepository.save(response);
    }

    @Override
    public List<SurveyResponse> getResponsesBySurveyId(Long surveyId) {
        return surveyResponseRepository.findBySurveyId(surveyId);
    }
}
