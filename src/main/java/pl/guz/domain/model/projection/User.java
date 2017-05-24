package pl.guz.domain.model.projection;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Table(name = "domain_user")
@EqualsAndHashCode(of = "businessId")
class User {

    @Id
    @GeneratedValue
    private Long id;
    private UUID businessId = UUID.randomUUID();
    private String name;
    @OneToMany
    @JoinTable(name = "domain_user_discount",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "discount_id")})
    private Set<Discount> discounts = new HashSet<>();

}
