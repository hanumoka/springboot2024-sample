package org.hanumoka.sample.member.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepo extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByUsername(String username);
}
