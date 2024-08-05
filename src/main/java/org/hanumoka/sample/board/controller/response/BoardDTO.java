package org.hanumoka.sample.board.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hanumoka.sample.account.presentation.rest.response.AccountResponseDto;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class BoardDTO {

    private long id;

    private String title;

    private String content;

    private AccountResponseDto author;

    private List<CommentDTO> commentDTOList;
}
