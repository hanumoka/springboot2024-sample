package org.hanumoka.sample.board.controller.response;

import lombok.*;
import org.hanumoka.sample.account.presentation.rest.response.AccountResponseDto;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Data
public class CommentDTO {
    private long id;
    private String content;
    private AccountResponseDto author;
}
