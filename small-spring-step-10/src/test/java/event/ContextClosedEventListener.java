package event;

import com.cz.springframework.context.ApplicationListener;
import com.cz.springframework.context.event.ContextClosedEvent;

/** @author ChangZhen */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件:::" + this.getClass().getName());
    }
}
