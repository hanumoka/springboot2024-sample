package org.hanumoka.sample.board.domain;

import lombok.Builder;
import org.hanumoka.sample.member.infrastructure.jpa.MemberEntity;
import org.hibernate.annotations.Comment;

import java.util.List;

public class Board {

    private Long id;

    private String title;

    private String content;

    private MemberEntity author;

    private List<Comment> comments;

    @Builder
    public Board(Long id, String title, String content, MemberEntity author, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.comments = comments;
    }
}
