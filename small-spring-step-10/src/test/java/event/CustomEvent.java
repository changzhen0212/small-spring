package event;

import com.cz.springframework.context.ApplicationEvent;

/**
 * 创建一个自定义事件，在事件类的构造函数中可以添加自己的想要的入参信息。
 *
 * <p>这个事件类最终会被完成的拿到监听里，所以你添加的属性都会被获得到
 *
 * @author ChangZhen
 */
public class CustomEvent extends ApplicationEvent {
    private Long id;
    private String message;

    public CustomEvent(Object source, Long id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
