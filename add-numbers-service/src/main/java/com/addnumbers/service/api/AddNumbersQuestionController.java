package com.addnumbers.service.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addnumbers.service.core.QuestionService;
import com.addnumbers.service.exception.IncorrectAnswerException;
import com.addnumbers.service.model.SecurityQuestion;

@RestController
@RequestMapping("/")
public class AddNumbersQuestionController {

    private final QuestionService questionService;

    public AddNumbersQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(value = "")
    public SecurityQuestion getQuestion(){
        return questionService.createQuestion();
    }

    @PutMapping(value = "")
    public SecurityQuestion answerQuestion(@RequestBody SecurityQuestion question) {
        if (!questionService.validateAnswer(question)) {
            throw new IncorrectAnswerException();
        }
        return question;
    }

}