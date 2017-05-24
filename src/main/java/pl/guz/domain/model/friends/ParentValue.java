package pl.guz.domain.model.friends;

import lombok.Value;

@Value
class ParentValue {

    private Long id;
    private String name;
    private FriendValue friendValue;
}
