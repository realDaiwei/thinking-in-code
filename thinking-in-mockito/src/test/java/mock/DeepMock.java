package mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import pojo.User;
import pojo.UserSearch;
import service.UserService;
import service.impl.UserServiceImpl;

import static org.mockito.Mockito.when;

/**
 * Created by Daiwei on 2021/3/28
 */
@RunWith(MockitoJUnitRunner.class)
public class DeepMock {

    private UserService userService;

    private UserSearch userSearch;

    @Before
    public void mock() {
        userService = Mockito.mock(UserService.class, Answers.RETURNS_DEEP_STUBS);
        userSearch = Mockito.mock(UserSearch.class);
    }

    @Test
    public void test() {
        when(userSearch.getId()).thenReturn(1);
        User daiwei = User.builder().username("daiwei").age(24).id(1).build();
        System.out.println(userService.selectRandomUser(userSearch));
    }
}
