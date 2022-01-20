package cn.mb.designpatterns.策略;

import cn.mb.designpatterns.策略.handler.AbstractMessageHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 消息处理
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2022/1/20
 */
@Component
public class MessageHandler implements
        ApplicationContextAware/** 获取ApplicationContext */,
        InitializingBean/** 初始化bean后做的事 */
{

    //spring容器
    private ApplicationContext context;

    /**
     * Map<Tag, MessageHandler>
     */
    private Map<String, AbstractMessageHandler> tagMessageHandlerMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        String[] beanNames = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(context, AbstractMessageHandler.class);
        for (int i = 0; i < beanNames.length; i++) {
            AbstractMessageHandler messageHandler = (AbstractMessageHandler) context.getBean(beanNames[i]);
            tagMessageHandlerMap.put(messageHandler.getTag(), messageHandler);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    //  获取对应标签的处理器来处理消息
    public void handle(String tag, String str) {
        tagMessageHandlerMap.get(tag).handle(str);
    }
}
