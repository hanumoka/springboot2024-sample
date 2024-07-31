package org.hanumoka.sample.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.member.infra.MemberEntity;
import org.hanumoka.sample.member.infra.MemberRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepo memberRepo;


    public Long createMember(String username, String name) {

        memberRepo.findByUsername(username).ifPresent(member -> {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        });

        MemberEntity member = MemberEntity.builder()
                .username(username)
                .name(name)
                .build();

        MemberEntity savedMember = memberRepo.save(member);
        return savedMember.getId();
    }

    public MemberEntity getMember(Long memberId) {
        return memberRepo.findById(memberId).orElse(null);
    }

    public long updateMember(Long memberId, String name) {
        MemberEntity member = memberRepo.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        member.setName(name);
        return member.getId();
    }

    public long deleteMember(Long memberId) {
        MemberEntity member = memberRepo.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        memberRepo.delete(member);
        return member.getId();
    }

    public Page<MemberEntity> getMemberAll(Pageable pageable) {
        return memberRepo.findAll(pageable);
    }
}
