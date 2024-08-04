package org.hanumoka.sample.small.member.domain;

import org.hanumoka.sample.member.domain.Member;
import org.hanumoka.sample.member.domain.vo.MemberCreate;
import org.hanumoka.sample.member.domain.vo.MemberUpdate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberTest {

    @Test
    public void Member는_MemberCreate_객체로_생성할_수_있다(){
        // given
        MemberCreate memberCreate = MemberCreate.builder()
                .username("hanumoka")
                .name("hanumoka")
                .build();

        // when
        Member member = Member.from(memberCreate);

        // then
        assertThat(member.getUsername()).isEqualTo("hanumoka");
        assertThat(member.getName()).isEqualTo("hanumoka");
    }

    @Test
    public void Member는_MemberUpdate_객체로_수정할_수_있다(){
        // given
        Member member = Member.builder()
                .id(1L)
                .username("hanumoka")
                .name("hanumoka")
                .build();

        MemberUpdate memberUpdate = MemberUpdate.builder()
                .id(1L)
                .username("hanumoka2")
                .name("hanumoka2")
                .build();

        // when
        Member updatedMember = member.update(memberUpdate);

        // then
        assertThat(updatedMember.getId()).isEqualTo(1L);
        assertThat(updatedMember.getUsername()).isEqualTo("hanumoka2");
        assertThat(updatedMember.getName()).isEqualTo("hanumoka2");
    }

}
