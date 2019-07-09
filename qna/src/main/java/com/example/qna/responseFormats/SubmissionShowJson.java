package com.example.qna.responseFormats;

import java.util.List;

import com.example.qna.entities.Answer;
import com.example.qna.entities.Question;

public class SubmissionShowJson {
  List<Question> questions;

  List<Answer> answers;

  public List<Question> getQuestions() {
    return this.questions;
  }

  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }

  public List<Answer> getAnswers() {
    return this.answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }

}