package com.example.qna.repositories;

import java.util.List;

import com.example.qna.entities.Question;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
  List<Question> findByUserId(long userId);

  List<Question> findByDescriptionContaining(String description);

}