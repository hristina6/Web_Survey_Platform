package com.example.survey_app.web;

import com.example.survey_app.models.Survey;
import com.example.survey_app.models.SurveyResponse;
import com.example.survey_app.models.User;
import com.example.survey_app.service.SurveyService;
import com.example.survey_app.service.SurveyResponseService;
import com.example.survey_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String submitResponse(@PathVariable Long id, @ModelAttribute SurveyResponse response, Authentication authentication) {
        Survey survey = surveyService.getSurveyById(id);
        response.setSurvey(survey);

        String username = authentication.getName();
        User user = userService.findByUsername(username);
        response.setUser(user);

        // Ensure responses are correctly processed and saved
        surveyResponseService.saveResponse(response);

        return "redirect:/user/surveys"; // Redirect to the list of surveys
    }

}
