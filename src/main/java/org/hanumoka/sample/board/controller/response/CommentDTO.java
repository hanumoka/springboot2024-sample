package org.hanumoka.sample.board.controller.response;

import lombok.*;
import org.hanumoka.sample.member.presentation.rest.response.MemberResponseDto;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Data
public class CommentDTO {
    private long id;
    private String content;
    private MemberResponseDto author;
}
