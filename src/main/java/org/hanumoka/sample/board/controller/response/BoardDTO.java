package org.hanumoka.sample.board.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hanumoka.sample.member.presentation.rest.response.MemberResponseDto;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class BoardDTO {

    private long id;

    private String title;

    private String content;

    private MemberResponseDto author;

    private List<CommentDTO> commentDTOList;
}
