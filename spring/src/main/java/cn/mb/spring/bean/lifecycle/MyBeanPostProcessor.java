package cn.mb.spring.bean.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  自定义BeanPostProcessor，自定义会覆盖默认的
 *  目前不知道能干嘛
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/15
 */
@Slf4j
//@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * bean执行{@link org.springframework.beans.factory.InitializingBean#afterPropertiesSet()}方法/自定义init-method 前 执行的方法
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("current bean is {}, postProcessBeforeInitialization", beanName);
        return bean;
    }

    /**
     * bean执行{@link org.springframework.beans.factory.InitializingBean#afterPropertiesSet()}方法/自定义init-method 后 执行的方法
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("current bean is {}, postProcessAfterInitialization", beanName);
        return bean;
    }

}
