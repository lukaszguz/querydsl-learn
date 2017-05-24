package pl.guz.domain.model.projection;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class UserRepository extends QueryDslRepositorySupport {

    public UserRepository() {
        super(User.class);
    }

    User findBy(Long id) {
        BooleanExpression expression = QUser.user.id.eq(id);
        return from(QUser.user)
                .where(expression).fetchOne();
    }

    List<User> findAll() {
        QUser user = QUser.user;
        return from(user).where(user.discounts.isNotEmpty()).fetch();
    }

    UserValue findValueBy(Long id) {
        QUser user = QUser.user;
        QDiscount discount = new QDiscount("discounts");
        return from(user)
                .join(user.discounts, discount)
                .where(user.id.eq(id))
                .transform(GroupBy.groupBy(user.id)
                        .as(
                                Projections.constructor(
                                        UserValue.class, user.businessId, user.name,
                                        GroupBy.set(Projections.constructor(
                                                DiscountValue.class, discount.businessId, discount.percent
                                        )))
                        )
                )
                .get(id);
    }
}
