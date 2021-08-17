package com.example.game.controller;

import com.example.game.dto.*;
import com.example.game.dtoService.QuestionDtoService;
import com.example.game.dtoService.UserDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class QuestionController {
    private List<String> inputLetters;
    private final QuestionDtoService questionDtoService;
    private final UserDtoService userDtoService;

    @Autowired
    public QuestionController(QuestionDtoService questionDtoService, UserDtoService userDtoService) {
        this.questionDtoService = questionDtoService;
        this.userDtoService = userDtoService;
    }

    @GetMapping("/questions")
    public String getStarted(@AuthenticationPrincipal Authentication authentication) {
        UserDto userDto = userDtoService.getCurrentUser(authentication);
        List<QuestionDto> questionDtos = questionDtoService.getAll(userDto.getId());
        Long randomId = questionDtos.get(new Random().nextInt(questionDtos.size())).getId();

        inputLetters = new ArrayList<>();

        return "redirect:/questions/" + randomId;
    }

    @PostMapping("/questions/check_letter")
    public String checkLetter(@ModelAttribute CheckWordDto checkWordDto, @AuthenticationPrincipal Authentication authentication) {
        QuestionDto questionDto = questionDtoService.getById(checkWordDto.getId());
        String username = userDtoService.getCurrentUser(authentication).getUsername();
        String letter = checkWordDto.getWord();

        String hiddenWord = questionDto.getWord();
        StringBuilder uniqueLetters = new StringBuilder();

        for (int i = 0; i < hiddenWord.length(); i++) {
            char current = hiddenWord.charAt(i);
            if (uniqueLetters.toString().indexOf(current) < 0) {
                uniqueLetters.append(current);
            } else {
                uniqueLetters.toString().replace(String.valueOf(current), "");
            }
        }

        if (letter.length() == 1) {
            inputLetters.add(checkWordDto.getWord());

            if (inputLetters.size() == uniqueLetters.length()) {
                questionDtoService.victory(username, questionDto.getId());
                return "redirect:/questions";
            }

            return "redirect:/questions/" + questionDto.getId();
        }

        if (!questionDto.getWord().contains(letter)) {
            return "redirect:/questions/" + questionDto.getId();
        }

        if (checkWordDto.getWord().length() == questionDto.getWord().length()) {
            if (checkWordDto.getWord().equalsIgnoreCase(questionDto.getWord())) {
                inputLetters = new ArrayList<>();

                for (char oneLetter : questionDto.getWord().toCharArray()) {
                    inputLetters.add(String.valueOf(oneLetter));
                }

                questionDtoService.victory(username, questionDto.getId());
            }
        }

        return "redirect:/questions/" + questionDto.getId();
    }

    @GetMapping("/questions/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        QuestionDto questionDto = questionDtoService.getById(id);
        CheckWordDto checkWordDto = new CheckWordDto();
        int count = questionDto.getWord().length();
        char[] letters = questionDto.getWord().toCharArray();

        model.addAttribute("question", questionDto);
        model.addAttribute("checkWordDto", checkWordDto);
        model.addAttribute("count", count);
        model.addAttribute("letters", letters);
        model.addAttribute("inputLetters", inputLetters);

        return "questions/details";
    }

    @GetMapping("questions/create")
    public String create(Model model) {
        CreateQuestionDto createQuestionDto = new CreateQuestionDto();
        model.addAttribute("createQuestionDto", createQuestionDto);

        return "questions/create";
    }

    @PostMapping("/questions/create")
    public String create(@ModelAttribute CreateQuestionDto createQuestionDto) {
        questionDtoService.create(createQuestionDto);

        return "redirect:/questions/";
    }

    @GetMapping("/questions/edit/{questionId}")
    public String update(@PathVariable("questionId") Long id, Model model) {
        UpdateQuestionDto updateQuestionDto = new UpdateQuestionDto();
        QuestionDto questionDto = questionDtoService.getById(id);

        model.addAttribute("updateQuestionDto", updateQuestionDto);
        model.addAttribute("questionDto", questionDto);

        return "questions/edit";
    }

    @PutMapping("/question/{questionId}")
    public String update(@ModelAttribute UpdateQuestionDto updateQuestionDto, @PathVariable("questionId") Long questionId) {
        questionDtoService.update(updateQuestionDto);
        return "redirect:/questions/";
    }

    @DeleteMapping("/questions/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        questionDtoService.delete(id);

        return "redirect:/questions";
    }
}