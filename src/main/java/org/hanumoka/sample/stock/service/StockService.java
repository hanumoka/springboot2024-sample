package org.hanumoka.sample.stock.service;

import org.hanumoka.sample.stock.domain.Stock;
import org.hanumoka.sample.stock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(final StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public synchronized void decrease(Long id, Long quantity) {

        //Stock 조회
        //재고를 감소시킨뒤
        //갱신된 값을 저장하도록 한다.
        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.decrease(quantity);
        stockRepository.saveAndFlush(stock);
    }
}
