package org.hanumoka.sample.board.domain;

import lombok.Builder;
import org.hanumoka.sample.account.domain.Account;

public class Comment {
    private Long id;

    private String content;

    private Board board;

    private Account author;

    @Builder
    public Comment(Long id, String content, Board board, Account author) {
        this.id = id;
        this.content = content;
        this.board = board;
        this.author = author;
    }
}
