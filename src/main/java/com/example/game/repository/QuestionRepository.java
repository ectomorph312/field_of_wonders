package com.example.game.repository;

import com.example.game.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT * FROM questions  WHERE id NOT IN (SELECT question_id FROM user_game_stories WHERE user_id = :userId)"
            , nativeQuery = true)
        List<Question> findAllNoTask(@Param("userId") Long userId);
}