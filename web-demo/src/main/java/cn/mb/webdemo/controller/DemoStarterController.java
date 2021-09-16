package cn.mb.webdemo.controller;

import cn.mb.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/8/23
 */
@RestController
@RequestMapping("/demoStarter")
public class DemoStarterController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/bb")
    public String bb() {
        return demoService.bb();
    }

}
