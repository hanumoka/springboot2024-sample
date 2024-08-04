package org.hanumoka.sample.small.member.presentation.response;

import org.hanumoka.sample.member.domain.Member;
import org.hanumoka.sample.member.presentation.rest.response.MemberResponseDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberResponseDtoTest {

    @Test
    public void Member으로_응답을_생성할_수_있다(){
        // given
        Member member = Member.builder()
                .id(1L)
                .username("hanumoka")
                .name("hanumoka")
                .build();

        // when
        MemberResponseDto memberResponseDto = MemberResponseDto.from(member);

        // then
        assertThat(memberResponseDto.getId()).isEqualTo(1L);
        assertThat(memberResponseDto.getUsername()).isEqualTo("hanumoka");
        assertThat(memberResponseDto.getName()).isEqualTo("hanumoka");
    }
}
