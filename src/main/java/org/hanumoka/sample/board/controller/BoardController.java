package org.hanumoka.sample.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.board.infra.jpa.BoardEntity;
import org.hanumoka.sample.board.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/boards")
@RestController
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Long> createBoard(@RequestBody CreateBoardRequestDto request) {
        Long boardId = boardService.createBoard(request.getAuthorId(), request.getTitle(), request.getContent());
        return ResponseEntity.ok(boardId);
    }

    @GetMapping("/get-all-board")
    public ResponseEntity<List<BoardDTO>> getAllBoard() {
        List<BoardEntity> boardEntityList =  boardService.getBoardAll();

        List<BoardDTO> result = boardEntityList.stream().map(board -> {
            return BoardDTO.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .authorId(board.getAuthor().getId())
                    .build();
        }).toList();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardDTO> getBoard(@PathVariable Long boardId) {
        BoardEntity board = boardService.getBoard(boardId);

        BoardDTO boardDTO = BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .authorId(board.getAuthor().getId())
                .build();

        return ResponseEntity.ok(boardDTO);
    }
//
//    @PostMapping("/{boardId}/comments")
//    public ResponseEntity<Void> addComment(@PathVariable Long boardId, @RequestBody AddCommentRequest request) {
//        boardService.addComment(boardId, request.getMemberId(), request.getContent());
//        return ResponseEntity.ok().build();
//    }
}
