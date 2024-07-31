package org.hanumoka.sample.member.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepo extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByUsername(String username);
}
