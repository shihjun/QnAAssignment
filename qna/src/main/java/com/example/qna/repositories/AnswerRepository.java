package com.example.qna.repositories;

import java.util.List;

import com.example.qna.entities.Answer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
  List<Answer> findByUserId(long userId);

  List<Answer> findAllByQuestionId(long questionId);

}