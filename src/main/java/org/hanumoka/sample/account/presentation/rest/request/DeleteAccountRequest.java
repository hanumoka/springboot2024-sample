package org.hanumoka.sample.account.presentation.rest.request;

import lombok.Data;
import lombok.Getter;
import org.hanumoka.sample.account.domain.Account;

@Getter
@Data
public class DeleteAccountRequest {
    private Long id;

    public Account toDomain() {
        return Account.builder()
                .id(id)
                .build();
    }
}
