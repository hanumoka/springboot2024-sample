package org.hanumoka.sample.board.domain;

import org.hanumoka.sample.member.domain.Member;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Long id;
    private String title;
    private String content;
    private Member author;
    private List<Comment> comments = new ArrayList<>();
}
