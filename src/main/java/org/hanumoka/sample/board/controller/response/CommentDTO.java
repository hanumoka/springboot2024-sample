package org.hanumoka.sample.board.controller.response;

import lombok.*;
import org.hanumoka.sample.member.controller.response.MemberDTO;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Data
public class CommentDTO {
    private long id;
    private String content;
    private MemberDTO author;
}
