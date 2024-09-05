package org.hanumoka.sample.testcontainer;

import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.account.application.port.out.AccountRepository;
import org.hanumoka.sample.account.infrastructure.jpa.entity.AccountEntity;
import org.hanumoka.sample.account.infrastructure.jpa.repository.AccountJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 클래스 설명란
 *
 * @author       : KYB
 * @since        : 24. 9. 5.
 */
@Slf4j
@ActiveProfiles("test")
@Transactional
@SpringBootTest
@Testcontainers
public class YourIntegrationTest {

    @Autowired
    private AccountJpaRepository accountJpaRepository;


//    @Container
//    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15")
//            .withDatabaseName("testdb")
//            .withUsername("testuser")
//            .withPassword("testpass")
//            .withInitScript("init.sql"); // 초기 데이터 스크립트
//
//    @DynamicPropertySource
//    static void registerPgProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
//        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
//        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
//    }

    @Test
    public void testSomething() {
        // 여기에 테스트 코드 작성
        log.info("Test code here");

        List<AccountEntity> all = accountJpaRepository.findAll();
        log.info("All size: {}", all.size());

        // 추가적인 검증 로직
        assertThat(all).isNotEmpty();
    }
}
