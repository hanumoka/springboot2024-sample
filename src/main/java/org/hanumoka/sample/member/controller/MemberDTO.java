package org.hanumoka.sample.member.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class MemberDTO {
    private long id;
    private String username;
    private String name;
}
