package org.hanumoka.sample.member.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.member.application.port.in.MemberCommentService;
import org.hanumoka.sample.member.application.port.out.MemberRepository;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberCommandServiceImpl implements MemberCommentService {

    private final MemberRepository memberRepository;

//    @Transactional
//    public Long createMember(MemberCreate memberCreate) {
//
//        memberRepo.findByUsername(memberCreate.getUsername()).ifPresent(member -> {
//            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
//        });
//
//        Member member = Member.from(memberCreate);
//
//        Member savedMember = memberRepo.save(member);
//        return savedMember.getId();
//    }
//
//    public Member getMember(Long memberId) {
//        return memberRepo.findById(memberId).orElse(null);
//    }
//
//    @Transactional
//    public long updateMember(MemberUpdate memberUpdate) {
//        Member member = memberRepo.findById(memberUpdate.getId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
//        member.update(memberUpdate);
//        memberRepo.save(member);
//        return member.getId();
//    }
//
//    @Transactional
//    public long deleteMember(Long memberId) {
//        Member member = memberRepo.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
//        memberRepo.delete(member);
//        return member.getId();
//    }

}
