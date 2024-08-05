package org.hanumoka.sample.board.domain;

import lombok.Builder;
import org.hanumoka.sample.account.infrastructure.jpa.AccountEntity;
import org.hibernate.annotations.Comment;

import java.util.List;

public class Board {

    private Long id;

    private String title;

    private String content;

    private AccountEntity author;

    private List<Comment> comments;

    @Builder
    public Board(Long id, String title, String content, AccountEntity author, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.comments = comments;
    }
}
