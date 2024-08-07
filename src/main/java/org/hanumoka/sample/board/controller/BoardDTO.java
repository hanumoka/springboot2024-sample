package org.hanumoka.sample.board.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hanumoka.sample.member.controller.MemberDTO;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class BoardDTO {

    private long id;

    private String title;

    private String content;

    private MemberDTO author;

    private List<CommentDTO> commentDTOList;
}
