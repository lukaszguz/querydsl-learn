package pl.guz.domain.model.projection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void should_find_all_users() {
        // when:
        List<User> users = userRepository.findAll();

        //then
        assertEquals(1, users.size());
    }


    @Test
    public void should_find_user_by_id() {
        // given:
        Long id = 1L;

        // when:
        User user = userRepository.findBy(id);

        //then
        assertEquals(id, user.getId());
        assertEquals("John", user.getName());
    }

    @Test
    public void should_find_user_value_by_id() {
        // given:
        Long id = 1L;

        // when:
        UserValue user = userRepository.findValueBy(id);

        //then
        assertNotNull(user.getBusinessId());
        assertEquals("John", user.getName());
        assertEquals(2, user.getDiscounts().size());
    }
}