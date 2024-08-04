package org.hanumoka.sample.member.domain;

import lombok.Getter;
import org.hanumoka.sample.common.type.GenderType;
import org.hanumoka.sample.common.domain.Email;
import org.hanumoka.sample.common.domain.MemberRole;
import org.hanumoka.sample.member.domain.type.MemberStatus;

import java.util.Set;

@Getter
public class Member {
    private MemberId id;
    private Email username;
    private String name;
    private Integer age;
    private GenderType gender;
    private MemberStatus status;
    private Set<MemberRole> Roles;
}
