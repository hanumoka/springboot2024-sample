package org.hanumoka.sample.board.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepo extends JpaRepository<BoardEntity, Long> {
}
