package org.hanumoka.sample.board.infra;

import lombok.RequiredArgsConstructor;
import org.hanumoka.sample.board.service.port.CommentRepo;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CommentRepoImpl implements CommentRepo {
    private final CommentJpaRepo commentJpaRepo;
}
