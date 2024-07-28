package org.hanumoka.sample.board.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.board.infra.jpa.BoardEntity;
import org.hanumoka.sample.board.infra.jpa.BoardRepo;
import org.hanumoka.sample.board.infra.jpa.CommentEntity;
import org.hanumoka.sample.board.infra.jpa.CommentRepo;
import org.hanumoka.sample.member.infra.jpa.MemberEntity;
import org.hanumoka.sample.member.infra.jpa.MemberRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepo boardRepo;
    private final CommentRepo commentRepo;
    private final MemberRepo memberRepo;

    @Transactional
    public Long createBoard(Long authorId, String title, String content) {
        MemberEntity memberEntity = memberRepo.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));

        BoardEntity boardEntity = BoardEntity.builder()
                .author(memberEntity)
                .title(title)
                .content(content)
                .build();

        boardRepo.save(boardEntity);

        return boardEntity.getId();
    }

    @Transactional
    public Long updateBoard(Long boardId, String title, String content) {
        BoardEntity boardEntity = boardRepo.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("Board not found"));

        boardEntity.setTitle(title);
        boardEntity.setContent(content);

        return boardEntity.getId();
    }

    @Transactional
    public Long deleteBoard(Long boardId) {
        BoardEntity boardEntity = boardRepo.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("Board not found"));

        boardRepo.delete(boardEntity);

        return boardEntity.getId();
    }

    public Page<BoardEntity> getBoardAll(Pageable pageable) {
        return boardRepo.findAll(pageable);
    }

    public List<BoardEntity> getBoardsByAuthorId(Long authorId) {
        return boardRepo.findAllByAuthorId(authorId);
    }

    public BoardEntity getBoard(Long boardId) {
        return boardRepo.findById(boardId)
                .orElse(null);
    }

    public CommentEntity getComment(Long commentId) {
        return commentRepo.findById(commentId).orElse(null);
    }

    public List<CommentEntity> getCommentByBoardId(Long boardId) {
        return commentRepo.findAllByBoardId(boardId);
    }

}
