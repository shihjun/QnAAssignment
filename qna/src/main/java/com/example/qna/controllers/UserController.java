package com.example.qna.controllers;

import java.util.List;

import com.example.qna.entities.Answer;
import com.example.qna.entities.Question;
import com.example.qna.entities.User;
import com.example.qna.repositories.AnswerRepository;
import com.example.qna.repositories.QuestionRepository;
import com.example.qna.repositories.UserRepository;
import com.example.qna.responseFormats.SubmissionShowJson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class UserController {

  @Autowired
  UserRepository userRepository;

  @Autowired
  AnswerRepository answerRepository;

  @Autowired
  QuestionRepository questionRepository;

  @PostMapping(value = "/users")
  public void addUser(@RequestBody User user) {
    userRepository.save(user);
  }

  @DeleteMapping(value = "/users/{id}")
  public void deleteUser(@PathVariable("id") long id) {
    User existingUser = userRepository.findById(id).orElse(new User());
    if (existingUser.getId() != null) {
      List<Answer> answers = answerRepository.findByUserId(existingUser.getId());
      answerRepository.deleteAll(answers);
      List<Question> questions = questionRepository.findByUserId(existingUser.getId());
      questionRepository.deleteAll(questions);
      userRepository.delete(existingUser);
    }
  }

  @GetMapping(value = "/users/{id}/submissions", produces = "application/json")
  public SubmissionShowJson displaySubmission(@PathVariable long id) {

    SubmissionShowJson json = new SubmissionShowJson();
    List<Question> questions = questionRepository.findByUserId(id);
    List<Answer> answers = answerRepository.findByUserId(id);

    json.setQuestions(questions);
    json.setAnswers(answers);

    return json;
  }

}