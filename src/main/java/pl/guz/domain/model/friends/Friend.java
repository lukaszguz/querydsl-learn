package pl.guz.domain.model.friends;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class Friend {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
