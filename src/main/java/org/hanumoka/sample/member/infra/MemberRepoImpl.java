package org.hanumoka.sample.member.infra;

import lombok.RequiredArgsConstructor;
import org.hanumoka.sample.member.domain.Member;
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
    public Optional<Member> findByUsername(String username) {
        return memberJpaRepo.findByUsername(username).map(MemberEntity::toDomain);
    }

    @Override
    public Member save(Member member) {
        return memberJpaRepo.save(MemberEntity.fromDomain(member)).toDomain();
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return memberJpaRepo.findById(memberId).map(MemberEntity::toDomain);
    }

    @Override
    public Page<Member> findAll(Pageable pageable) {
        return memberJpaRepo.findAll(pageable).map(MemberEntity::toDomain);
    }

    @Override
    public void delete(Member member) {
        memberJpaRepo.delete(MemberEntity.fromDomain(member));
    }
}
