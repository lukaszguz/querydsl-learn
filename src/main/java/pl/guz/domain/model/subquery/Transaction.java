package pl.guz.domain.model.subquery;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@Table(name = "domain_transaction")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "commissions")
@ToString
class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @Enumerated(STRING)
    private TransactionType transactionType;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Commission> commissions;

    public Transaction(TransactionType transactionType, List<Commission> commissions, BigDecimal amount) {
        this.transactionType = transactionType;
        this.commissions = commissions;
        this.amount = amount;
    }

    public Transaction(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
