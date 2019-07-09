package com.example.qna.controllers;

import java.util.List;

import com.example.qna.entities.Question;
import com.example.qna.repositories.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class QuestionController {

  @Autowired
  QuestionRepository questionRepository;

  @PostMapping(value = "/questions")
  public void addQuestion(@RequestBody Question question) {
    questionRepository.save(question);
  }

  @GetMapping(value = "/questions", produces = "application/json")
  public List<Question> displayQuestionByDesc(@RequestParam(required = false) String description) {

    if (description != null) {
      return questionRepository.findByDescriptionContaining(description);
    }
    return questionRepository.findAll();
  }

}