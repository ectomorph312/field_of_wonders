package com.example.game.dtoService;

import com.example.game.dto.CreateQuestionDto;
import com.example.game.dto.QuestionDto;
import com.example.game.dto.UpdateQuestionDto;

import java.util.List;

public interface QuestionDtoService {
    QuestionDto create(CreateQuestionDto createQuestionDto);
    QuestionDto update(UpdateQuestionDto updateQuestionDto);
    List<QuestionDto> getAll(Long userId);
    QuestionDto getById(Long id);
    String getUniqueCharacters(String word);
    void victory(String username, Long questionId);
    void delete(Long id);
}