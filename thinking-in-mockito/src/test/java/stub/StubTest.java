package stub;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import pojo.UserSearch;
import service.UserService;
import service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;

/**
 * Created by Daiwei on 2021/3/28
 */
@RunWith(MockitoJUnitRunner.class)
public class StubTest {

    private List<String> list;

    @Before
    public void init() {
        this.list = Mockito.mock(ArrayList.class);
    }

    @Test
    public void howToUseStub() {
        Mockito.when(list.get(0)).thenReturn("first");
        Assert.assertThat(list.get(0), equalTo("first"));
        Mockito.when(list.get(Matchers.anyInt())).thenThrow(new RuntimeException());

        try {
            list.get(0);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertThat(e, instanceOf(RuntimeException.class));
        }
    }

    @Test
    public void howToUseStubForVoid() {
        Mockito.doNothing().when(list).clear();
        list.clear();
        Mockito.verify(list, Mockito.times(1)).clear();

        Mockito.doThrow(new RuntimeException()).when(list).clear();
        try {
            list.clear();
            Assert.fail();
        } catch (Exception e) {
            Assert.assertThat(e, instanceOf(RuntimeException.class));
        }
    }

    @Test
    public void useStubReturn() {
        Mockito.doReturn("first").when(list).get(0);
        //等价
//        Mockito.when(list.get(0)).thenReturn("first");
        Assert.assertThat(list.get(0), equalTo("first"));
    }

    @Test
    public void iteratorStub() {
        Mockito.when(list.get(Matchers.anyInt())).thenReturn("first", "second", "third");
        Assert.assertThat(list.get(1), equalTo("first"));
        Assert.assertThat(list.get(2), equalTo("second"));
        Assert.assertThat(list.get(3), equalTo("third"));
        // 每次get 和 顺序有关，和idx 无关，因为 Matchers.anyInt()
        Assert.assertThat(list.get(1), equalTo("third"));
    }


    @Test
    public void stubbingWithAnswer() {
        Mockito.when(list.get(Matchers.anyInt())).thenAnswer(new Answer<String>() {

            // 重写方法动态mock
            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                int arg = (int) invocationOnMock.getArguments()[0];
                return arg + "--hello";
            }
        });
        Assert.assertThat(list.get(3), equalTo("3--hello"));
        Assert.assertThat(list.get(4), equalTo("4--hello"));
    }


    @Test
    public void testRunner() {
        UserService userService = Mockito.mock(UserServiceImpl.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(userService.selectRandomUser(null)).thenCallRealMethod();
        Assert.assertThat(userService.selectRandomUser(null), equalTo(null));

    }

    @After
    public void clear() {
        Mockito.reset(this.list);
    }

}
