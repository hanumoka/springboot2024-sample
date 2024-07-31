package org.hanumoka.sample.member.service.port;

import org.hanumoka.sample.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MemberRepo {
    Optional<Member> findByUsername(String username);

    Member save(Member member);

    Optional<Member> findById(Long memberId);

    Page<Member> findAll(Pageable pageable);

    void delete(Member member);
}
