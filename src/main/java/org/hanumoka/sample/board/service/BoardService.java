package org.hanumoka.sample.board.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.board.controller.response.BoardDTO;
import org.hanumoka.sample.board.controller.response.CommentDTO;
import org.hanumoka.sample.board.infra.BoardEntity;
import org.hanumoka.sample.board.infra.BoardRepo;
import org.hanumoka.sample.board.infra.CommentEntity;
import org.hanumoka.sample.board.infra.CommentRepo;
import org.hanumoka.sample.member.controller.response.MemberDTO;
import org.hanumoka.sample.member.infra.MemberEntity;
import org.hanumoka.sample.member.infra.MemberRepo;
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

    public List<BoardDTO> getAllBoard(){
        return boardRepo.findAll().stream()
                .map(boardEntity -> {
                    MemberEntity author = boardEntity.getAuthor();
                    MemberDTO authorDTO = MemberDTO.builder()
                            .id(author.getId())
                            .name(author.getName())
                            .build();

                    List<CommentEntity> commentEntityList = commentRepo.findAllByBoardId(boardEntity.getId());
                    List<CommentDTO> commentDTOList = commentEntityList.stream()
                            .map(commentEntity -> CommentDTO.builder()
                                    .id(commentEntity.getId())
                                    .content(commentEntity.getContent())
                                    .author(MemberDTO.builder()
                                            .id(commentEntity.getAuthor().getId())
                                            .name(commentEntity.getAuthor().getName())
                                            .build())
                                    .build())
                            .toList();

                    return BoardDTO.builder()
                            .id(boardEntity.getId())
                            .title(boardEntity.getTitle())
                            .content(boardEntity.getContent())
                            .author(authorDTO)
                            .commentDTOList(commentDTOList)
                            .build();
                })
                .toList();
    }

    public Page<BoardDTO> getBoardPage(Pageable pageable) {
        Page<BoardEntity> boardEntityList =  boardRepo.findAll(pageable);

        return boardEntityList.map(boardEntity -> {
            MemberEntity author = boardEntity.getAuthor();
            MemberDTO authorDTO = MemberDTO.builder()
                    .id(author.getId())
                    .name(author.getName())
                    .build();

            List<CommentEntity> commentEntityList = commentRepo.findAllByBoardId(boardEntity.getId());
            List<CommentDTO> commentDTOList = commentEntityList.stream()
                    .map(commentEntity -> CommentDTO.builder()
                            .id(commentEntity.getId())
                            .content(commentEntity.getContent())
                            .author(MemberDTO.builder()
                                    .id(commentEntity.getAuthor().getId())
                                    .name(commentEntity.getAuthor().getName())
                                    .build())
                            .build())
                    .toList();

            return BoardDTO.builder()
                    .id(boardEntity.getId())
                    .title(boardEntity.getTitle())
                    .content(boardEntity.getContent())
                    .author(authorDTO)
                    .commentDTOList(commentDTOList)
                    .build();
        });
    }

    public List<BoardEntity> getBoardsByAuthorId(Long authorId) {
        return boardRepo.findAllByAuthorId(authorId);
    }

    public BoardDTO getBoard(Long boardId) {
        BoardEntity boardEntity = boardRepo.findById(boardId)
                .orElse(null);

        if(boardEntity == null) {
            return null;
        }

        MemberEntity author = boardEntity.getAuthor();
        MemberDTO authorDTO = MemberDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .build();

        List<CommentEntity> commentEntityList = commentRepo.findAllByBoardId(boardId);
        List<CommentDTO> commentDTOList = commentEntityList.stream()
                .map(commentEntity -> CommentDTO.builder()
                        .id(commentEntity.getId())
                        .content(commentEntity.getContent())
                        .author(MemberDTO.builder()
                                .id(commentEntity.getAuthor().getId())
                                .name(commentEntity.getAuthor().getName())
                                .build())
                        .build())
                .toList();

        return BoardDTO.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .author(authorDTO)
                .commentDTOList(commentDTOList)
                .build();
    }

    public CommentEntity getComment(Long commentId) {
        return commentRepo.findById(commentId).orElse(null);
    }

    public List<CommentEntity> getCommentByBoardId(Long boardId) {
        return commentRepo.findAllByBoardId(boardId);
    }

}
