package test.bean;

import com.cz.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * 这是一个实现接口 FactoryBean 的代理类 ProxyBeanFactory 名称， 主要是模拟了 UserDao 的原有功能，类似于 MyBatis 框架中的代理操作。
 *
 * <p>getObject() 中提供的就是一个 InvocationHandler 的代理对象，当有方法调用的时候，则执行代理对象的功能。
 *
 * @author ChangZhen
 */
public class ProxyBeanFactory implements FactoryBean<IUserDao> {

    @Override
    public IUserDao getObject() throws Exception {
        InvocationHandler handler =
                (proxy, method, args) -> {
                    Map<String, String> hashMap = new HashMap<>();
                    hashMap.put("10001", "张三");
                    hashMap.put("10002", "李四");
                    hashMap.put("10003", "小明");
                    return "你被代理了:::" + method.getName() + ": " + hashMap.get(args[0].toString());
                };
        return (IUserDao)
                Proxy.newProxyInstance(
                        Thread.currentThread().getContextClassLoader(),
                        new Class[] {IUserDao.class},
                        handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
