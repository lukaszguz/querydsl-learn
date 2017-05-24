package pl.guz.domain.model.projection;

import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@EqualsAndHashCode(of = "businessId")
class Discount {

    @Id
    @GeneratedValue
    private Long id;
    private UUID businessId = UUID.randomUUID();
    private Short percent;
}
