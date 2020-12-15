package cn.mb.rocketmq.controller;

import cn.mb.rocketmq.component.TradeProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  测试发送消息
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/14
 */
@RestController
public class SendController {

    private final TradeProducer tradeProducer;

    public SendController(TradeProducer tradeProducer) {
        this.tradeProducer = tradeProducer;
    }

    @GetMapping("/cancelOrder/{orderSn}")
    public void cancelOrder(@PathVariable("orderSn") String orderSn) {
        tradeProducer.sendCancelOrderMsg(orderSn);
    }
}
