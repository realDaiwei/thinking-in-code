import mapper.UserMapper;
import mapper.impl.UserMapperImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pojo.User;
import pojo.UserSearch;
import service.UserService;
import service.impl.UserServiceImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by Daiwei on 2021/3/27
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private UserMapperImpl userMapper;

    private UserSearch userSearch;

    private UserService userService;

    @Before
    public void mock() {
        this.userMapper = Mockito.mock(UserMapperImpl.class);
        this.userSearch = Mockito.mock(UserSearch.class);
        this.userService = new UserServiceImpl(this.userMapper);
    }

    @Test
    public void testNormal() {
        when(userSearch.getId()).thenReturn(1);
        User daiwei = User.builder().username("daiwei").age(24).id(1).build();
        when(userMapper.selectById(Matchers.anyInt())).thenReturn(daiwei);
        User user = userService.selectRandomUser(userSearch);
        assertThat(user.getId(), equalTo(1));
        assertThat(user.getUsername(), equalTo("daiwei"));
        assertThat(user.getAge(), equalTo(24));
    }

    @Test
    public void testNotFindUser() {
//        when(userSearch).thenReturn(null);
//        when(userMapper.selectById(Matchers.eq(2))).thenReturn(null);
        User user = userService.selectRandomUser(null);
        assertThat(user, equalTo(null));
    }



}


