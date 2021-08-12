package com.example.game.mapper;

import com.example.game.dto.QuestionDto;
import com.example.game.entity.Question;

import java.util.List;

public interface QuestionMapper {
    QuestionDto toQuestionDto(Question question);
    List<QuestionDto> toQuestionDtos(List<Question> questions);
}