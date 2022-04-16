import com.cz.springframework.test.bean.IUserService;
import com.cz.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/** @author ChangZhen */
public class ApiTest {

    @Test
    public void test_aop() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = context.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }
}
