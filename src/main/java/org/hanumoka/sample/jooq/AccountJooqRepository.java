package org.hanumoka.sample.jooq;

import lombok.RequiredArgsConstructor;
import org.hanumoka.sample.infrastructure.jooq.generated.tables.JAccount;
import org.hanumoka.sample.infrastructure.jooq.generated.tables.daos.AccountDao;
import org.hanumoka.sample.infrastructure.jooq.generated.tables.pojos.AccountPojo;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AccountJooqRepository {

    private final AccountDao accountDao;

    private final DSLContext dslContext;
    private final JAccount jAccount = JAccount.ACCOUNT;

    public AccountPojo findById(Long id) {
        AccountPojo account =  dslContext.select(jAccount.fields())
                .from(jAccount)
                .where(jAccount.ID.eq(id))
                .fetchOneInto(AccountPojo.class);

        return account;
    }

    public SimpleAccount findSimpleAccountById(Long id) {
        SimpleAccount simpleAccount =  dslContext.select(jAccount.ID, jAccount.USERNAME)
                .from(jAccount)
                .where(jAccount.ID.eq(id))
                .fetchOneInto(SimpleAccount.class);

        return simpleAccount;
    }

    public void findAccountList(Long page, Long size) {
    }

}
