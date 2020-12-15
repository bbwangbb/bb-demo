package cn.mb.rocketmq.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>
 *  取消订单消费者
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/14
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "${rocketmq.msg-type.cancel-order.consumer-group}", topic = "${rocketmq.topic}", selectorExpression = "${rocketmq.msg-type.cancel-order.tag}")
public class CancelOrderConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String orderSn) {
        log.info("current time is {}, consumer, current orderSn is {}", LocalDateTime.now(), orderSn);
    }
}
