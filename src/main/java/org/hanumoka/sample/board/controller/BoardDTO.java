package org.hanumoka.sample.board.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class BoardDTO {

    private long id;

    private String title;

    private String content;

    private long authorId;
}
