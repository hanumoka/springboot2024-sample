package org.hanumoka.sample.board.domain;

import org.hanumoka.sample.member.domain.Member;

public class Comment {
    private Long id;
    private String content;
    private Board board;
    private Member author;
}
