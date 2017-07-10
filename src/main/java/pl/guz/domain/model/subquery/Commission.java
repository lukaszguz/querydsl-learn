package pl.guz.domain.model.subquery;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.EnumType.STRING;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "transaction")
@ToString(exclude = "transaction")
class Commission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @Enumerated(STRING)
    private CommissionType commissionType;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    public Commission(CommissionType commissionType, Transaction transaction, BigDecimal amount) {
        this.commissionType = commissionType;
        this.transaction = transaction;
        this.amount = amount;
    }
}
