package mock;

import mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pojo.User;

/**
 * Created by Daiwei on 2021/3/28
 */
@RunWith(MockitoJUnitRunner.class)
public class AnnotationMock {

    private UserMapper userMapper;

    @Before
    public void mock() {
        userMapper = Mockito.mock(UserMapper.class, Answers.RETURNS_SMART_NULLS);
    }

    @Test
   public void testMock() {
       User user = userMapper.selectById(1);
       System.out.println(user);
   }



}
