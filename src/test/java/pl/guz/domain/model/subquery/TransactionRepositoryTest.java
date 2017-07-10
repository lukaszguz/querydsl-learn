package pl.guz.domain.model.subquery;

import com.querydsl.core.Tuple;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.guz.domain.model.subquery.CommissionType;
import pl.guz.domain.model.subquery.Transaction;
import pl.guz.domain.model.subquery.TransactionRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void should_find_all_transactions_that_total_amount_is_greater_than_and_commission_is_partner_type() {
        // given
        BigDecimal amount = new BigDecimal("11.01");

        // when
        List<Tuple> transactions = transactionRepository.findByPartnerCommissionAndCalculateTotalAmount(amount);

        //then
        transactions.forEach(tuple -> {
            BigDecimal totalAmount = tuple.get(5, BigDecimal.class);
            log.info("Total amount: {}, compare to: {}", totalAmount, totalAmount.compareTo(amount));
            Assert.assertTrue(totalAmount.compareTo(amount) > 0);
        });
        Assert.assertEquals(2, transactions.size());
    }

    @Test
    public void should_find_all_transactions_that_have_partner_commision_or_have_not_any_commissions() {
        // when
        List<Transaction> transactions = transactionRepository.findAllWithPartnerCommissionOrIfHasNotCommission();

        //then
        transactions.forEach(trx -> {
            boolean isEmpty = trx.getCommissions().isEmpty();
            boolean onlyPartnerCommissions = trx.getCommissions()
                    .stream()
                    .filter(commission -> !Objects.equals(commission.getCommissionType(), CommissionType.PARTNER))
                    .count() == 0;
            Assert.assertTrue(isEmpty || onlyPartnerCommissions);
        });
        Assert.assertEquals(3, transactions.size());
    }

}