package pl.guz.domain.model.projection;

import lombok.Value;

import java.util.UUID;

@Value
public class DiscountValue {

    private UUID businessId;
    private Short percent;
}
