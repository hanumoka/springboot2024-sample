package org.hanumoka.sample.board.infra;

import lombok.RequiredArgsConstructor;
import org.hanumoka.sample.board.service.port.BoardRepo;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BoardRepoImpl implements BoardRepo {
    private final BoardJpaRepo boardJpaRepo;
}
