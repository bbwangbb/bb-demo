package cn.mb.designpatterns.策略.handler;

import lombok.Data;

/**
 * <p>
 * 抽象消息处理器
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2022/1/20
 */
@Data
public abstract class AbstractMessageHandler {

    private String tag;

    public AbstractMessageHandler() {
    }

    public abstract void handle(String str);
}
