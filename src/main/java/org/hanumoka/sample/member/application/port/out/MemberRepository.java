package org.hanumoka.sample.member.application.port.out;

import org.hanumoka.sample.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MemberRepository {
    Optional<Member> findByUsername(String username);

    Member save(Member member);

    Optional<Member> findById(Long memberId);

    Page<Member> findAll(Pageable pageable);

    void delete(Member member);
}
