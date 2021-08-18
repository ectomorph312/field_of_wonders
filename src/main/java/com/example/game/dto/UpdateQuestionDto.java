package com.example.game.dto;

import lombok.Data;

@Data
public class UpdateQuestionDto {
    private Long id;
    private String description;
    private String word;
}