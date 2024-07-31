package org.hanumoka.sample.comment.domain;

import lombok.Builder;
import org.hanumoka.sample.board.domain.Board;
import org.hanumoka.sample.member.domain.Member;

public class Comment {
    private Long id;

    private String content;

    private Board board;

    private Member author;

    @Builder
    public Comment(Long id, String content, Board board, Member author) {
        this.id = id;
        this.content = content;
        this.board = board;
        this.author = author;
    }
}
