package com.example.survey_app.service;

import com.example.survey_app.models.Feedback;

import java.util.List;

public interface FeedbackService {
    void saveFeedback(Feedback feedback);
    List<Feedback> getAllFeedback();
}
