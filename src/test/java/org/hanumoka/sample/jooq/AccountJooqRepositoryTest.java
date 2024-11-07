package org.hanumoka.sample.jooq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountJooqRepositoryTest {

    @Autowired
    private AccountJooqRepository accountJooqRepository;

    @Test
    void findById() {
        assertNotNull(accountJooqRepository.findById(1L));
    }

    @Test
    void findSimpleAccountById() {
        assertNotNull(accountJooqRepository.findSimpleAccountById(1L));
    }

}