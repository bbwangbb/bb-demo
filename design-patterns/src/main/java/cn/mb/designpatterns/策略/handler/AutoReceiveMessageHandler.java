package cn.mb.designpatterns.策略.handler;

import org.springframework.stereotype.Component;

/**
 * <p>
 * 自动收货消息处理器
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2022/1/20
 */
@Component
public class AutoReceiveMessageHandler extends AbstractMessageHandler {

    /**
     * 自动收货标签
     */
    private static final String TAG = "AUTO_RECEIVE";

    public AutoReceiveMessageHandler() {
        setTag(TAG);
    }

    @Override
    public void handle(String str) {
        System.out.println("自动收货消息消费逻辑，接收字符串：" + str);
    }

}
