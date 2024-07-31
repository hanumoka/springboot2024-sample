package org.hanumoka.sample.member.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberUpdate {
    private Long id;
    private String username;
    private String name;

    @Builder
    public MemberUpdate(Long id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }
}
