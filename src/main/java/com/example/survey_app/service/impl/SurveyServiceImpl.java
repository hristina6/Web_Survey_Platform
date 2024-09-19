package com.example.survey_app.service.impl;

import com.example.survey_app.models.*;
import com.example.survey_app.repository.AnswerRepository;
import com.example.survey_app.repository.QuestionRepository;
import com.example.survey_app.repository.SurveyRepository;
import com.example.survey_app.repository.SurveyResponseRepository;
import com.example.survey_app.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyResponseRepository surveyResponseRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public void createSurvey(Survey survey) {
        survey.getQuestions().forEach(question -> {
            if (question.getAnswers() == null) {
                question.setAnswers(new ArrayList<>());
            } else {
                question.getAnswers().forEach(answer -> answer.setQuestion(question));
            }
        });

        Survey savedSurvey = surveyRepository.save(survey);

        savedSurvey.getQuestions().forEach(question -> question.setSurvey(savedSurvey));
        surveyRepository.save(savedSurvey);
    }

    @Override
    public void publishSurvey(Long id) {
        Survey survey = surveyRepository.findById(id).orElseThrow(InvalidParameterException::new);
        survey.setPublished(true);
        surveyRepository.save(survey);
    }

    @Override
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public Survey getSurveyById(Long id) {
        // Use the custom query to fetch the survey along with its questions
        return surveyRepository.findByIdWithQuestions(id)
                .orElseThrow(() -> new RuntimeException("Survey not found"));
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

    @Override
    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }

    @Override
    public void unpublishSurvey(Long id) {
        Survey survey = surveyRepository.findById(id).orElseThrow(InvalidParameterException::new);
        survey.setPublished(false);
        surveyRepository.save(survey);
    }

    public void updateSurvey(Long id, Survey updatedSurvey) {
        Optional<Survey> existingSurvey = surveyRepository.findById(id);
        if (existingSurvey.isPresent()) {
            Survey survey = existingSurvey.get();
            survey.setTitle(updatedSurvey.getTitle());
            survey.setDescription(updatedSurvey.getDescription());
            survey.setQuestions(updatedSurvey.getQuestions());
            surveyRepository.save(survey);
        }
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Question not found"));
    }

    public Answer getAnswerById(Long id) {
        return answerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Answer not found"));
    }

    @Override
    public List<Survey> getSurveysByUser(User user) {
        return surveyRepository.findByUser(user);
    }

}
