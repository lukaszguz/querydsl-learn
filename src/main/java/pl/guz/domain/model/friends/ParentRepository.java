package pl.guz.domain.model.friends;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
class ParentRepository extends QueryDslRepositorySupport {

    public ParentRepository() {
        super(Parent.class);
    }

    public Parent findBy(Long id) {
        BooleanExpression expression = QParent.parent.id.eq(id);
        return from(QParent.parent)
                .where(expression).fetchOne();
    }

    public ParentValue findValue(Long id) {
        QParent qParent = QParent.parent;
        return from(qParent)
                .select(Projections.constructor(ParentValue.class, qParent.id, qParent.name, Projections.constructor(FriendValue.class, qParent.friend.id, qParent.friend.name)))
                .where(qParent.id.eq(id)).fetchOne();
    }
}
