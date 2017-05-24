package pl.guz.domain.model.projection;

import lombok.Value;

import java.util.Set;
import java.util.UUID;

@Value
public class UserValue {
    private UUID businessId;
    private String name;
    private Set<Discount> discounts;
}
