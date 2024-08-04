package org.hanumoka.sample.member.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.member.domain.Member;
import org.hanumoka.sample.member.application.port.in.MemberQueryService;
import org.hanumoka.sample.member.application.port.out.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    public Page<Member> getMemberAll(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }
}
