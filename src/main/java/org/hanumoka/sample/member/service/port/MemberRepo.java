package org.hanumoka.sample.member.service.port;

import org.hanumoka.sample.member.infra.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MemberRepo {
    Optional<MemberEntity> findByUsername(String username);

    MemberEntity save(MemberEntity member);

    Optional<MemberEntity> findById(Long memberId);

    Page<MemberEntity> findAll(Pageable pageable);

    void delete(MemberEntity member);
}
