package org.hanumoka.sample.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.board.infra.jpa.BoardRepo;
import org.hanumoka.sample.board.infra.jpa.CommentRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {
    private final BoardRepo boardRepo;
    private final CommentRepo commentRepo;
}
