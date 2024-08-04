package org.hanumoka.sample.member.domain.vo;

import lombok.Builder;

public class MemberCreate {
    private String username;
    private String name;

    @Builder
    public MemberCreate(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }
}
