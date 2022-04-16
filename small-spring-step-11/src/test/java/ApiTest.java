import beans.IUserService;
import beans.UserService;
import beans.UserServiceInterceptor;
import com.cz.springframework.aop.AdvisedSupport;
import com.cz.springframework.aop.TargetSource;
import com.cz.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.cz.springframework.aop.framework.Cglib2AopProxy;
import com.cz.springframework.aop.framework.JdkDynamicAopProxy;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/** @author ChangZhen */
public class ApiTest {

    @Test
    public void test_proxy_method() {
        // 目标对象，可以替换成任何的目标对象
        Object targetObj = new UserService();
        // aop 代理
        Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                targetObj.getClass().getInterfaces(),
                new InvocationHandler() {
                    // new AspectJExpressionPointcut
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        // 方法匹配器

                        return null;
                    }
                });
    }

    @Test
    public void test_dynamic() {
        //    目标对象
        IUserService userService = new UserService();

        //    组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(
                new AspectJExpressionPointcut("execution(* beans.IUserService.*(..))"));
        //    jdk代理对象、
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        //    测试调用
        System.out.println("result: " + proxy_jdk.queryUserInfo());

        // cglib代理对象
        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        System.out.println("result: " + proxy_cglib.queryUserInfo());
    }
}
