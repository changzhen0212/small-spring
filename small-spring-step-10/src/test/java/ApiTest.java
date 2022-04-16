import com.cz.springframework.context.support.ClassPathXmlApplicationContext;
import event.CustomEvent;
import org.junit.Test;

/**
 * @author ChangZhen
 */
public class ApiTest {

    @Test
    public void text_event(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.publisherEvent(new CustomEvent(context, 111111L, "事件成功了！"));
        context.registerShutdownHook();
    }

}
