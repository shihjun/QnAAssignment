package com.example.qna.controllers;

import java.util.List;

import com.example.qna.entities.Answer;
import com.example.qna.entities.Question;
import com.example.qna.repositories.AnswerRepository;
import com.example.qna.repositories.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class AnswerController {

  @Autowired
  AnswerRepository answerRepository;

  @Autowired
  QuestionRepository questionRepository;

  @PostMapping(value = "/questions/{id}/answers")
  public void addAnswerForQuestion(@RequestBody Answer answer, @PathVariable("id") long id) {
    Question question = questionRepository.findById(id).orElse(new Question());
    if (question.getId() != null) {
      answer.setQuestionId(id);
      answerRepository.save(answer);
    }
  }

  @PostMapping(value = "/answers/{id}")
  public void updateAnswer(@RequestBody Answer answer, @PathVariable("id") long id) {
    Answer existingAnswer = answerRepository.findById(id).orElse(new Answer());
    if (existingAnswer.getId() != null) {
      answer.setId(id);
      answer.setUserId(existingAnswer.getUserId());
      answer.setQuestionId(existingAnswer.getQuestionId());
      answerRepository.save(answer);
    }
  }

  @GetMapping(value = "/questions/{id}/answers", produces = "application/json")
  public List<Answer> displayAnswers(@PathVariable("id") long id) {
    return answerRepository.findAllByQuestionId(id);
  }

}