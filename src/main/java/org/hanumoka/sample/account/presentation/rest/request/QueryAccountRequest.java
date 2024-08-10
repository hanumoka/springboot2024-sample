package org.hanumoka.sample.account.presentation.rest.request;

import lombok.*;

@Getter
@Data
public class QueryAccountRequest {
    private Long id;
    private String username;
    private String accountUuid;
}
