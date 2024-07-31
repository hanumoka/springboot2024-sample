package org.hanumoka.sample.comment.infra;

import lombok.RequiredArgsConstructor;
import org.hanumoka.sample.comment.service.port.CommentRepo;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CommentRepoImpl implements CommentRepo {
    private final CommentJpaRepo commentJpaRepo;
}