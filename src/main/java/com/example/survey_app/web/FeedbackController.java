package com.example.survey_app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.survey_app.models.Feedback;
import com.example.survey_app.service.FeedbackService;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/feedback")
    public String submitFeedback(@ModelAttribute Feedback feedback) {
        feedbackService.saveFeedback(feedback);
        return "feedback";
    }

    @GetMapping("/feedbacks")
    public String getFeedbacks(Model model) {
        model.addAttribute("feedbackList", feedbackService.getAllFeedback());
        return "index";
    }
}
