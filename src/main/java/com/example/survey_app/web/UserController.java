package com.example.survey_app.web;

import com.example.survey_app.models.*;
import com.example.survey_app.service.SurveyService;
import com.example.survey_app.service.SurveyResponseService;
import com.example.survey_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private SurveyResponseService surveyResponseService;

    @Autowired
    private UserService userService;

    @GetMapping("/surveys")
    public String listSurveys(Model model) {
        model.addAttribute("surveys", surveyService.getAllSurveys());
        return "user/surveys";
    }

    @GetMapping("/surveys/{id}")
    public String viewSurvey(@PathVariable Long id, Model model) {
        model.addAttribute("survey", surveyService.getSurveyById(id));
        model.addAttribute("responses", new SurveyResponse());
        return "user/view_survey";
    }

    @PostMapping("/surveys/{id}/responses")
    public String submitResponse(@PathVariable Long id, @RequestParam Map<String, String> allParams, Authentication authentication) {
        Survey survey = surveyService.getSurveyById(id);
        SurveyResponse response = new SurveyResponse();
        response.setSurvey(survey);

        String username = authentication.getName();
        User user = userService.findByUsername(username);
        response.setUser(user);

        List<ResponseEntry> responseEntries = new ArrayList<>();

        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            ResponseEntry responseEntry = new ResponseEntry();
            responseEntry.setSurveyResponse(response);

            if (key.startsWith("question-")) {
                Long questionId = Long.parseLong(key.substring("question-".length()));
                Long answerId = Long.parseLong(value);

                Question question = surveyService.getQuestionById(questionId);
                Answer answer = surveyService.getAnswerById(answerId);

                responseEntry.setQuestion(question);
                responseEntry.setAnswer(answer);

            } else if (key.startsWith("open-")) {
                Long questionId = Long.parseLong(key.substring("open-".length()));

                Question question = surveyService.getQuestionById(questionId);

                responseEntry.setQuestion(question);
                responseEntry.setOpenText(value); // Ensure `openText` is set
            }

            responseEntries.add(responseEntry);
        }

        response.setResponses(responseEntries);

        // Save the response and its entries
        surveyResponseService.saveResponse(response);

        return "redirect:/user/surveys"; // Redirect to the list of surveys
    }

}