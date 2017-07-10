package pl.guz.domain.model.subquery;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Order(101)
@Slf4j
@AllArgsConstructor
class TransactionService {

    private TransactionRepository transactionRepository;


    @PostConstruct
    public void init() {
        doing();
    }

    @Transactional
    public void doing() {
        log.info("start2");
        Transaction transaction = transactionRepository.findBy(1L);
        List<Transaction> transactions = transactionRepository.findAllWithPartnerCommissionOrIfHasNotCommission();
        log.info("Trx: {}", transaction);
        log.info("Trxs: {}", transactions);
    }
}
