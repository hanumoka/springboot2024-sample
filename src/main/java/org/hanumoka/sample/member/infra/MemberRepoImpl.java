package org.hanumoka.sample.member.infra;

import lombok.RequiredArgsConstructor;
import org.hanumoka.sample.member.service.port.MemberRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberRepoImpl implements MemberRepo {
    private final MemberJpaRepo memberJpaRepo;

    @Override
    public Optional<MemberEntity> findByUsername(String username) {
        return memberJpaRepo.findByUsername(username);
    }

    @Override
    public MemberEntity save(MemberEntity member) {
        return memberJpaRepo.save(member);
    }

    @Override
    public Optional<MemberEntity> findById(Long memberId) {
        return memberJpaRepo.findById(memberId);
    }

    @Override
    public Page<MemberEntity> findAll(Pageable pageable) {
        return memberJpaRepo.findAll(pageable);
    }

    @Override
    public void delete(MemberEntity member) {
        memberJpaRepo.delete(member);
    }
}
