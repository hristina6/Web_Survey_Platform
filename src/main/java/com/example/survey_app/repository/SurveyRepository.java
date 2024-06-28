package com.example.survey_app.repository;

import com.example.survey_app.models.Survey;
import com.example.survey_app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    List<Survey> findByUser(User user);
}