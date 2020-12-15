package cn.mb.rocketmq.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>
 *  交易消息生产者
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/14
 */
@Slf4j
@Component
public class TradeProducer {

    @Value("${rocketmq.topic}")
    private String topic;
    @Value("${rocketmq.producer.send-message-timeout}")
    private long sendMessageTimeout;
    @Value("${rocketmq.msg-type.cancel-order.tag}")
    private String cancelOrderTag;
    private final RocketMQTemplate rocketMQTemplate;

    public TradeProducer(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public void sendCancelOrderMsg(String orderSn) {
        log.info("current time is {}, params is {}", LocalDateTime.now(), orderSn);

        /** 异步发送延迟消息 */
        rocketMQTemplate.asyncSend(topic + ":" + cancelOrderTag, new GenericMessage<>(orderSn), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("send result is {}", sendResult.getSendStatus());
            }

            @Override
            public void onException(Throwable e) {
                log.error("send error, cause by {}, param is {}", e.getMessage(), orderSn);
            }
        }, sendMessageTimeout, 2);

        /** 同步发送消息 */
//        SendResult sendResult = rocketMQTemplate.syncSend(topic + ":" + cancelOrderTag, orderSn);

        /** 发送单向信息(即不等待Broker响应) */
//        rocketMQTemplate.sendOneWay(topic + ":" + cancelOrderTag, orderSn);

        /**  有用CountDownLatch上锁，响应有问题，搞不懂{@link org.apache.rocketmq.client.producer.RequestResponseFuture#waitResponseMessage} */
//        rocketMQTemplate.sendAndReceive(topic + ":" + cancelOrderTag, orderSn, String.class, 300000, 2);
    }
}
