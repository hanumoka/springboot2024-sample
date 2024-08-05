package org.hanumoka.sample.account.presentation.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class CreateAccountRequest {
    private String username;
    private String name;
}
