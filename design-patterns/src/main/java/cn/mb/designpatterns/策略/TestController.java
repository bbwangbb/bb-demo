package cn.mb.designpatterns.策略;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2022/1/20
 */
@RestController
@AllArgsConstructor
public class TestController {

    private final MessageHandler messageHandler;

    @GetMapping("/consume")
    public void 模拟MQ消费(@RequestParam("tag") String tag, @RequestParam("str") String str) {
        messageHandler.handle(tag, str);
    }

}
