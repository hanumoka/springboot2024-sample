package org.hanumoka.sample.member.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<MemberEntity, Long> {
}
