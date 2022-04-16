package com.cz.springframework.test;

import com.cz.springframework.beans.factory.config.BeanDefinition;
import com.cz.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.cz.springframework.test.bean.UserService;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * @author ChangZhen
 * @date 2021/11/6
 */
public class ApiTest {

    @Test
    public void test_BeanFactory() {
        //  1.初始化beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 可以手动指定jdk实例化或者cglib实例化
        // beanFactory.setInstantiationStrategy(new SimpleInstantiationStrategy());
        //  2.注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registryBeanDefinition("userService", beanDefinition);
        //  3.获取bean，传入参数
        UserService userService = (UserService) beanFactory.getBean("userService", "ChangZhen");
        userService.queryUserInfo();

        // 4. 第二次调用，即使是无参的，因为是从单例池中取，拿到的还是之前的bean对象
        UserService userService1 = (UserService) beanFactory.getBean("userService");
        userService1.queryUserInfo();

        System.out.println("=============");
        System.out.println(userService);
        System.out.println(userService1);
    }

    /** 调用newInstance()无参构造方法创建对象 */
    @Test
    public void test_newInstance() throws IllegalAccessException, InstantiationException {
        UserService userService = UserService.class.newInstance();
        userService.queryUserInfo(); // 打印结果
    }

    /** 通过有参构造方法实例化 */
    @Test
    public void test_constructor() throws Exception {
        Class<UserService> userServiceClass = UserService.class;
        // 获取声明的构造方法,指定参数类型，通过传递参数进行实例化
        Constructor<UserService> declaredConstructor =
                userServiceClass.getDeclaredConstructor(String.class);
        UserService userService = declaredConstructor.newInstance("changzhen");
        System.out.println(userService); // 打印结果 UserService[name='changzhen']
        userService.queryUserInfo(); // 打印结果 查询用户信息：changzhen
    }

    /** 获取构造方法信息 实例化bean */
    @Test
    public void test_parameterTypes() throws Exception {
        Class<UserService> userServiceClass = UserService.class;
        // 获取全部声明的构造方法
        Constructor<?>[] declaredConstructors = userServiceClass.getDeclaredConstructors();
        // 获取第一个构造方法
        Constructor<?> constructor = declaredConstructors[0];
        // 获取构造方法参数类型
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        // 指定参数类型获取bean的构造方法
        Constructor<UserService> declaredConstructor =
                userServiceClass.getDeclaredConstructor(parameterTypes);
        // 通过newInstance实例化bean
        UserService userService = declaredConstructor.newInstance("ChangZhen");
        System.out.println(userService); // 打印结果 UserService[name='ChangZhen']
    }

    /** 使用cglib实例化bean */
    @Test
    public void test_cglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(
                new NoOp() {
                    @Override
                    public int hashCode() {
                        return super.hashCode();
                    }
                });
        Object obj = enhancer.create(new Class[] {String.class}, new Object[] {"changzhen"});
        System.out.println(obj); // 打印结果 UserService[name='changzhen']
    }
}
