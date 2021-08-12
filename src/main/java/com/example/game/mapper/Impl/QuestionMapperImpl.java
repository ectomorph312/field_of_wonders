package com.example.game.mapper.Impl;

import com.example.game.dto.QuestionDto;
import com.example.game.entity.Question;
import com.example.game.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionMapperImpl implements QuestionMapper {
    @Override
    public QuestionDto toQuestionDto(Question question) {
        QuestionDto questionDto = new QuestionDto();

        questionDto.setId(question.getId());
        questionDto.setDescription(question.getDescription());
        questionDto.setWord(question.getWord());

        return questionDto;
    }

    @Override
    public List<QuestionDto> toQuestionDtos(List<Question> questions) {
        List<QuestionDto> questionDtos = new ArrayList<>();

        questions.forEach(question -> {
            QuestionDto questionDto = this.toQuestionDto(question);

            questionDtos.add(questionDto);
        });

        return questionDtos;
    }
}