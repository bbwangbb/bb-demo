package cn.mb.spring.bean.lifecycle;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

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
@NoArgsConstructor
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
