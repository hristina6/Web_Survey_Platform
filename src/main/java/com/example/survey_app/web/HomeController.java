package com.example.survey_app.web;

import com.example.survey_app.models.Feedback;
import com.example.survey_app.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/")
    public String home(Model model) {
        List<Feedback> feedbackList = feedbackService.getAllFeedback();
        model.addAttribute("feedbackList", feedbackList);
        return "index";
    }


    @GetMapping("/feedback")
    public String feedback(){
        return "feedback";
    }

}
