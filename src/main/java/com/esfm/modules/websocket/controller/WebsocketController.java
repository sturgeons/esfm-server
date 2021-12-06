package com.esfm.modules.websocket.controller;

import com.esfm.utils.YaoSay;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class WebsocketController {
    @MessageMapping("/test")
    public String test(String data) {
        return "测试成功";
    }

    /**
     * @description 方法描述 根据设置此处应该是 app/hello
     * /topic/getResponse 为订阅
     * @author zhangqiang
     * @date 2019年06月06日 17:03
     */
    @MessageMapping("/hello")
    @SendTo("/topic/getResponse")
    public String sub(String msg)  {
        return msg;
    }
//看板信息中心
    @MessageMapping("/dashboard")
    @SendTo("/topic/dashboard")
    public String dashboard(String msg) {
        return msg;
    }

    @MessageMapping("welcome")
    public String say() throws Exception {
        Thread.sleep(1000);
        return "成功";
    }

    @MessageMapping("/message")
    @SendToUser("/message")
    public String userGetMessage() {
        return "message";

    }
}
