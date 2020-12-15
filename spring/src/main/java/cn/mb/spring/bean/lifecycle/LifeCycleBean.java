package cn.mb.spring.bean.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  bean的生命周期利用
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/15
 */
@Slf4j
@Component
public class LifeCycleBean implements InitializingBean, DisposableBean {

//    public LifeCycleBean() {
//        log.info("LifeCycleBean construct without param");
//    }

    public LifeCycleBean(Integer aaaa) {
        log.info("LifeCycleBean construct with int param");
    }

    public LifeCycleBean(String aaaa) {
        log.info("LifeCycleBean construct with str param");
    }

    public void hello() {
        System.err.println("hello");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        /**
         * <p>
         *  bean初始化后调用
         * </p>
         */
        log.info("afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        log.info("destroy");
    }
}
