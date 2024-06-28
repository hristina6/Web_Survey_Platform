package com.example.survey_app.web;


import com.example.survey_app.models.Answer;
import com.example.survey_app.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping
    public List<Answer> getAllAnswers() {
        return answerService.findAll();
    }

    @GetMapping("/{id}")
    public Answer getAnswerById(@PathVariable Long id) {
        return answerService.findById(id);
    }

    @PostMapping
    public Answer createAnswer(@RequestBody Answer answer) {
        return answerService.save(answer);
    }

    @DeleteMapping("/{id}")
    public void deleteAnswer(@PathVariable Long id) {
        answerService.deleteById(id);
    }
}