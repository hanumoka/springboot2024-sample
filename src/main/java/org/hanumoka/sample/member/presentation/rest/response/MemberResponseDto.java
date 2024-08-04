package org.hanumoka.sample.member.presentation.rest.response;

import lombok.Builder;
import lombok.Getter;
import org.hanumoka.sample.member.domain.Member;

@Builder
@Getter
public class MemberResponseDto {
    private long id;
    private String username;
    private String name;

    public static MemberResponseDto from(Member member){
        return MemberResponseDto.builder()
                .id(member.getId())
                .username(member.getUsername())
                .name(member.getName())
                .build();
    }
}
