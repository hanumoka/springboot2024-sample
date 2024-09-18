package org.hanumoka.sample.stock.service;

import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.stock.domain.Stock;
import org.hanumoka.sample.stock.repository.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
//@ActiveProfiles("test")
@SpringBootTest
public class StockServiceTest {

    @Autowired
    private StockService stockService;

    @Autowired
    private PessimisticLockStockService pessimisticLockStockService;

    @Autowired
    private StockRepository stockRepository;

    @BeforeEach
    public void before(){
        stockRepository.deleteAll();
        stockRepository.saveAndFlush(new Stock(1L, 5000L));
    }

    @AfterEach
    public void after(){
        stockRepository.deleteAll();
    }

    @Test
    public void 재고감소(){
        log.info("재고감소 테스트 시작");

        stockService.decrease(1L, 1L);

        Stock stock = stockRepository.findById(1L).get();
        assertEquals(99L, stock.getQuantity());
    }

    @Test
    public void 동시에_100개의_요청() throws InterruptedException {

        int threadCount = 5000;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int i =0; i< threadCount; i++){
            executorService.execute(() -> {
                try{
//                    stockService.decrease(1L, 1L);
                    pessimisticLockStockService.decrease(1L, 1L);
                }catch (Exception e){
                    log.error(e.getMessage());
                }finally {
                    countDownLatch.countDown();
                }//finally
            });
        }//for

        countDownLatch.await();

        Stock stock = stockRepository.findById(1L).orElseThrow();
        // 100 - (1 * 100) = 0 를 예상 => 그러나 레이스 컨디션 발생
        assertEquals(0L, stock.getQuantity());
    }//

}