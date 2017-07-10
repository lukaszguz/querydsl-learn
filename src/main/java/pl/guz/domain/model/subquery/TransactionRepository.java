package pl.guz.domain.model.subquery;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Slf4j
class TransactionRepository extends QueryDslRepositorySupport {
    public TransactionRepository() {
        super(Transaction.class);
    }

    Transaction findBy(Long id) {
        QTransaction transaction = QTransaction.transaction;
        BooleanExpression expression = transaction.id.eq(id);
        return from(transaction)
                .innerJoin(transaction.commissions)
                .fetchJoin()
                .where(expression).fetchOne();
    }

    List<Tuple> findByPartnerCommissionAndCalculateTotalAmount(BigDecimal goe) {
        QTransaction qTransaction = QTransaction.transaction;
        QCommission qCommission = new QCommission("commission");
        NumberExpression<BigDecimal> totalAmount = new CaseBuilder()
                .when(qCommission.isNull()).then(BigDecimal.ZERO)
                .otherwise(qCommission.amount)
                .add(qTransaction.amount);

        return from(qTransaction)
                .select(qTransaction.id, qTransaction.amount, qCommission.id, qCommission.commissionType, qCommission.amount, totalAmount)
                .leftJoin(qTransaction.commissions, qCommission)
                .where(
                        qCommission.isNull().or(qCommission.commissionType.eq(CommissionType.PARTNER))
                                .and(totalAmount.goe(goe))
                )
                .fetch();
    }

    List<Transaction> findAllWithPartnerCommissionOrIfHasNotCommission() {
        QTransaction qTransaction = QTransaction.transaction;
        QCommission qCommission = new QCommission("commission");
        return from(qTransaction)
                .leftJoin(qTransaction.commissions, qCommission)
                .fetchJoin()
                .where(
                        qCommission.isNull().or(qCommission.commissionType.eq(CommissionType.PARTNER))
                )
                .fetch();
    }
}
