package org.hanumoka.sample.member.domain;

import lombok.Builder;
import lombok.Getter;
import org.hanumoka.sample.board.domain.Board;

import java.util.List;

@Getter
public class Member {
    private final Long id;
    private final String username;
    private final String name;
    private final List<Board> boards; //TODO: ??? 다른 도메인을 참조하는것은 옳은가? 패키지가 다른데?

    @Builder
    public Member(Long id, String username, String name, List<Board> boards) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.boards = boards;
    }

    public static Member from(MemberCreate memberCreate) {
        return Member.builder()
                .username(memberCreate.getUsername())
                .name(memberCreate.getName())
                .build();
    }

    public Member update(MemberUpdate memberUpdate) {
        return Member.builder()
                .id(memberUpdate.getId())
                .username(memberUpdate.getUsername())
                .name(memberUpdate.getName())
                .build();
    }
}
