package pl.guz.domain.model.friends;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
class Parent {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    @OneToOne
    private Friend friend;
}
