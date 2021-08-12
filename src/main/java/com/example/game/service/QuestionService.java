package com.example.game.service;

import com.example.game.entity.Question;

import java.util.List;

public interface QuestionService {
    Question getById(Long id);
    List<Question> getAll();
    Question save(Question question);
    void delete(Long id);
    List<Question> findAllNoTask(Long userId);
}