package org.hanumoka.sample.account.presentation.rest.request;

import lombok.*;

import java.util.Optional;

@Getter
@Data
public class PageAccountRequestDto {
    private Long id;
    private String username;
    private String accountUuid;
}
