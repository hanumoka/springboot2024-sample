package org.hanumoka.sample.member.application.specification;

import lombok.RequiredArgsConstructor;
import org.hanumoka.sample.member.application.port.out.MemberRepository;
import org.hanumoka.sample.member.application.port.in.MemberSpecification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberSpecificationImpl implements MemberSpecification {
    private final MemberRepository memberRepository;
}
