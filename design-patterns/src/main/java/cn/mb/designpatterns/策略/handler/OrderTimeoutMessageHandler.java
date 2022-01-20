package cn.mb.designpatterns.策略.handler;

import org.springframework.stereotype.Component;

/**
 * <p>
 * 订单超时消息处理器
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2022/1/20
 */
@Component
public class OrderTimeoutMessageHandler extends AbstractMessageHandler {

    /**
     * 订单超时标签
     */
    private static final String TAG = "ORDER_TIMEOUT";

    public OrderTimeoutMessageHandler() {
        setTag(TAG);
    }

    @Override
    public void handle(String str) {
        System.out.println("订单超时消息消费逻辑，接收字符串：" + str);
    }
}
