package com.example.game.dtoService.Impl;

import com.example.game.dto.CreateQuestionDto;
import com.example.game.dto.QuestionDto;
import com.example.game.dto.UpdateQuestionDto;
import com.example.game.dtoService.QuestionDtoService;
import com.example.game.entity.Question;
import com.example.game.entity.User;
import com.example.game.mapper.QuestionMapper;
import com.example.game.service.QuestionService;
import com.example.game.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionDtoServiceImpl implements QuestionDtoService {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;
    private final UserService userService;

    @Autowired
    public QuestionDtoServiceImpl(QuestionService questionService, QuestionMapper questionMapper, UserService userService) {
        this.questionService = questionService;
        this.questionMapper = questionMapper;
        this.userService = userService;
    }

    @Override
    public QuestionDto create(CreateQuestionDto createQuestionDto) {
        Question question = new Question();

        question.setDescription(createQuestionDto.getDescription());
        question.setWord(createQuestionDto.getWord());

        return questionMapper.toQuestionDto(questionService.save(question));
    }

    @Override
    public QuestionDto update(UpdateQuestionDto updateQuestionDto) {
        Question updQuestion = questionService.getById(updateQuestionDto.getId());

        if (updateQuestionDto.getDescription() != null) {
            updQuestion.setDescription(updateQuestionDto.getDescription());
        }

        if (updateQuestionDto.getWord() != null) {
            updQuestion.setWord(updateQuestionDto.getWord());
        }

        return questionMapper.toQuestionDto(questionService.save(updQuestion));
    }

    @Override
    public List<QuestionDto> getAll(Long userId) {
        return questionMapper.toQuestionDtos(questionService.findAllNoTask(userId));
    }

    @Override
    public QuestionDto getById(Long id) {
        return questionMapper.toQuestionDto(questionService.getById(id));
    }

    @Override
    public void victory(String username, Long questionId) {
        User user = userService.getByUsername(username);
        Question question = questionService.getById(questionId);

        question.getUsers().add(user);

        user.getQuestions().add(question);

        questionService.save(question);
    }

    @Override
    public void delete(Long id) {
        questionService.delete(id);
    }
}