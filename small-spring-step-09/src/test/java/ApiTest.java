import com.cz.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import test.bean.UserService;

/** @author ChangZhen */
public class ApiTest {

    @Test
    public void test_prototype() {
        // 1.初始化beanFactory
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.registerShutdownHook();

        // 2.获取bean对象调用方法
        UserService userService01 = context.getBean("userService", UserService.class);
        UserService userService02 = context.getBean("userService", UserService.class);

        // 3.配置scope = "prototype/singleton"
        System.out.println(userService01);
        System.out.println(userService02);

        // 4. 打印哈希值
        System.out.println(
                userService01 + " 十六进制hash：" + Integer.toHexString(userService01.hashCode()));
        System.out.println(
                userService02 + " 十六进制hash：" + Integer.toHexString(userService02.hashCode()));
        System.out.println(ClassLayout.parseInstance(userService01).toPrintable());
        System.out.println(ClassLayout.parseInstance(userService02).toPrintable());
    }

    @Test
    public void test_factory_bean() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.registerShutdownHook();

        UserService userService = context.getBean("userService", UserService.class);
        System.out.println("测试结果:::" + userService.queryUserInfo());
    }
}
