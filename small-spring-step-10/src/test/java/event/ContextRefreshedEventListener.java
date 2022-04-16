package event;

import com.cz.springframework.context.ApplicationListener;
import com.cz.springframework.context.event.ContextRefreshedEvent;

/** @author ChangZhen */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件:::" + this.getClass().getName());
    }
}
