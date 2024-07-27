package org.hanumoka.sample.board.controller;

import lombok.Data;

@Data
public class CreateBoardRequestDto {
    private Long authorId;
    private String title;
    private String content;
}
