package org.hanumoka.sample.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.board.controller.response.BoardDTO;
import org.hanumoka.sample.board.controller.request.CreateBoardRequestDto;
import org.hanumoka.sample.board.controller.request.UpdateBoardRequestDto;
import org.hanumoka.sample.board.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/boards")
@RestController
public class BoardController {

    private final BoardService boardService;

    //생성
    @PostMapping
    public ResponseEntity<Long> createBoard(@RequestBody CreateBoardRequestDto request) {
        Long boardId = boardService.createBoard(request.getAuthorId(), request.getTitle(), request.getContent());
        return ResponseEntity.ok(boardId);
    }

    //단건 조회
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardDTO> getBoard(@PathVariable Long boardId) {
        BoardDTO boardDTO = boardService.getBoard(boardId);
        return ResponseEntity.ok(boardDTO);
    }
    
    //단건 수정
    @PostMapping("/{boardId}")
    public ResponseEntity<Long> updateBoard(@PathVariable Long boardId, @RequestBody UpdateBoardRequestDto request) {
        long updatedBoardId = boardService.updateBoard(boardId, request.getTitle(), request.getContent());
        return ResponseEntity.ok(updatedBoardId);
    }
    
    //단건 삭제
    @PostMapping("/{boardId}/delete")
    public ResponseEntity<Long> deleteBoard(@PathVariable Long boardId) {
        Long deletedBoardId = boardService.deleteBoard(boardId);
        return ResponseEntity.ok(deletedBoardId);
    }

    //페이징 조회
    @GetMapping("/get-all-board")
    public ResponseEntity<Page<BoardDTO>> getAllBoard(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        Page<BoardDTO> boardEntityList =  boardService.getBoardPage(pageable);
        return ResponseEntity.ok(boardEntityList);
    }

}
