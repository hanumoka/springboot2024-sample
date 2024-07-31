package org.hanumoka.sample.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.member.domain.Member;
import org.hanumoka.sample.member.domain.MemberCreate;
import org.hanumoka.sample.member.domain.MemberUpdate;
import org.hanumoka.sample.member.service.port.MemberRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepo memberRepo;

    @Transactional
    public Long createMember(MemberCreate memberCreate) {

        memberRepo.findByUsername(memberCreate.getUsername()).ifPresent(member -> {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        });

        Member member = Member.from(memberCreate);

        Member savedMember = memberRepo.save(member);
        return savedMember.getId();
    }

    public Member getMember(Long memberId) {
        return memberRepo.findById(memberId).orElse(null);
    }

    @Transactional
    public long updateMember(MemberUpdate memberUpdate) {
        Member member = memberRepo.findById(memberUpdate.getId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        member.update(memberUpdate);
        memberRepo.save(member);
        return member.getId();
    }

    @Transactional
    public long deleteMember(Long memberId) {
        Member member = memberRepo.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        memberRepo.delete(member);
        return member.getId();
    }

    public Page<Member> getMemberAll(Pageable pageable) {
        return memberRepo.findAll(pageable);
    }
}
