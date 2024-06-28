package com.example.survey_app.web;

import com.example.survey_app.models.Survey;
import com.example.survey_app.models.SurveyResponse;
import com.example.survey_app.service.SurveyResponseService;
import com.example.survey_app.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private SurveyResponseService surveyResponseService;

    @GetMapping("/surveys")
    public String listSurveys(Model model) {
        model.addAttribute("surveys", surveyService.getAllSurveys());
        return "admin/surveys";
    }

    @GetMapping("/surveys/new")
    public String createSurveyForm(Model model) {
        model.addAttribute("survey", new Survey());
        return "admin/create_survey";
    }

    @PostMapping("/surveys")
    public String createSurvey(@ModelAttribute Survey survey) {
        surveyService.createSurvey(survey);
        return "redirect:/admin/surveys";
    }

    @PostMapping("/surveys/publish/{id}")
    public String publishSurvey(@PathVariable Long id) {
        surveyService.publishSurvey(id);
        return "redirect:/admin/surveys";
    }

    @GetMapping("/surveys/results/{id}")
    public String viewResults(@PathVariable Long id, Model model) {
        List<SurveyResponse> responses = surveyService.getSurveyResponses(id);
        model.addAttribute("responses", responses);
        return "admin/view_results";
    }
}
