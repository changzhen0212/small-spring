package com.cz.springframework.aop.framework.autoproxy;

import com.cz.springframework.aop.AdvisedSupport;
import com.cz.springframework.aop.Advisor;
import com.cz.springframework.aop.ClassFilter;
import com.cz.springframework.aop.Pointcut;
import com.cz.springframework.aop.TargetSource;
import com.cz.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.cz.springframework.aop.framework.ProxyFactory;
import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.PropertyValues;
import com.cz.springframework.beans.factory.BeanFactory;
import com.cz.springframework.beans.factory.BeanFactoryAware;
import com.cz.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.cz.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;

/**
 * 融入 Bean 生命周期的自动代理创建者
 *
 * <p>类的主要核心实现在于 postProcessBeforeInstantiation 方法中，
 *
 * <p>从通过 beanFactory.getBeansOfType 获取 AspectJExpressionPointcutAdvisor 开始
 *
 * <p>获取了 advisors 以后就可以遍历相应的 AspectJExpressionPointcutAdvisor
 * 填充对应的属性信息，包括：目标对象、拦截方法、匹配器，之后返回代理对象即可。
 *
 * <p>那么现在调用方获取到的这个 Bean 对象就是一个已经被切面注入的对象了，当调 用方法的时候，则会被按需拦截，处理用户需要的信息。
 *
 * @author ChangZhen
 */
public class DefaultAdvisorAutoProxyCreator
        implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName)
            throws BeansException {
        if (isInfrastructureClass(beanClass)) {
            return null;
        }
        Collection<AspectJExpressionPointcutAdvisor> advisors =
                beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(beanClass)) {
                continue;
            }
            AdvisedSupport advisedSupport = new AdvisedSupport();
            TargetSource targetSource = null;
            try {
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);
            return new ProxyFactory(advisedSupport).getProxy();
        }
        return null;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return pvs;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass)
                || Pointcut.class.isAssignableFrom(beanClass)
                || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }
}
