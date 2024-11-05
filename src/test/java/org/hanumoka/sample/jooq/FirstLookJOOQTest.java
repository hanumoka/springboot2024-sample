package org.hanumoka.sample.jooq;

import org.hanumoka.sample.infrastructure.jooq.generated.tables.JAccount;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FirstLookJOOQTest {

    @Autowired
    DSLContext dslContext;

    @Test
    void test() {
//        System.out.println(dslContext.select().from("account").fetch());
        dslContext.selectFrom(JAccount.ACCOUNT)
                .limit(4).fetch();
    }
}
