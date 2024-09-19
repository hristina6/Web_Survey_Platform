package com.example.survey_app.web;

import com.example.survey_app.models.Survey;
import com.example.survey_app.models.SurveyResponse;
import com.example.survey_app.models.User;
import com.example.survey_app.repository.SurveyRepository;
import com.example.survey_app.service.SurveyResponseService;
import com.example.survey_app.service.SurveyService;
import com.example.survey_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private SurveyResponseService surveyResponseService;

    @Autowired
    private UserService userService;

    @GetMapping("/surveys")
    public String listSurveys(Model model) {
        String username = userService.getCurrentUsername();
        User currentUser = userService.findByUsername(username);

        List<Survey> surveys = surveyService.getSurveysByUser(currentUser);
        model.addAttribute("username", username);
        model.addAttribute("surveys", surveys);

        if (surveys.isEmpty()) {
            model.addAttribute("noSurveysMessage", "You haven't created any surveys yet.");
        }

        return "admin/surveys";
    }

    @GetMapping("/surveys/new")
    public String createSurveyForm(Model model) {
        model.addAttribute("survey", new Survey());
        return "admin/create_survey";
    }

    @PostMapping("/surveys")
    public String createSurvey(@ModelAttribute Survey survey) {
        String username = userService.getCurrentUsername();
        User currentUser = userService.findByUsername(username);

        survey.setUser(currentUser);
        surveyService.createSurvey(survey);

        return "redirect:/admin/surveys";
    }

    @PostMapping("/surveys/publish/{id}")
    public String publishSurvey(@PathVariable Long id) {
        surveyService.publishSurvey(id);
        return "redirect:/admin/surveys";
    }

    @PostMapping("/surveys/unpublish/{id}")
    public String unpublishSurvey(@PathVariable Long id) {
        surveyService.unpublishSurvey(id);
        return "redirect:/admin/surveys";
    }



    @PostMapping("/surveys/delete/{id}")
    public String deleteSurvey(@PathVariable Long id) {
        surveyService.deleteSurvey(id);
        return "redirect:/admin/surveys";
    }

    @GetMapping("/surveys/view/{id}")
    public String viewSurvey(@PathVariable Long id, Model model) {
        Survey survey = surveyService.getSurveyById(id);
        model.addAttribute("survey", survey);
        return "admin/view_survey";
    }

    @GetMapping("/surveys/edit/{id}")
    public String editSurvey(@PathVariable Long id, Model model) {
        Survey survey = surveyService.getSurveyById(id);
        model.addAttribute("survey", survey);
        return "admin/edit_survey";
    }

    @PostMapping("/surveys/{id}")
    public String updateSurvey(@PathVariable Long id, @ModelAttribute Survey survey) {
        surveyService.updateSurvey(id, survey);
        return "redirect:/admin/surveys";
    }

    @GetMapping("/surveys/results/{id}")
    public String viewResults(@PathVariable Long id, Model model) {
        List<SurveyResponse> responses = surveyResponseService.getResponsesBySurveyId(id);
        model.addAttribute("responses", responses);
        return "admin/view_results";
    }
}
