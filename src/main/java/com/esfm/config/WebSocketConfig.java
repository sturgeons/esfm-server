package com.esfm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    /**
     * 注册stomp的端点
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 用户订阅主题的前缀 /topic 代表发布广播，即群发 /queue 代表点对点，即发指定用户
        registry.addEndpoint("/webSocket")
                // 设置跨域
                .setAllowedOriginPatterns("*")
                //添加socket拦截器，用于从请求中获取客户端标识参数 目前没什么用，可以去掉
                .withSockJS();
    }

    /**
     * 配置消息代理(message broker)
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        ThreadPoolTaskScheduler te = new ThreadPoolTaskScheduler();
//        te.setPoolSize(1);
//        te.setThreadNamePrefix("wss-heartbeat-thread-");
//        te.initialize();
        // 代理用不到
//        registry.enableStompBrokerRelay().setClientLogin("111").setClientPasscode("111").setRelayPort(8005);
        // 订阅Broker名称
        registry.enableSimpleBroker("/queue", "/topic")
        // 第一个参数表示服务器写入或发送心跳的频率。 第二个参数表示客户端发送心跳时间
//                .setHeartbeatValue(new long[]{15000,3000})
//                .setTaskScheduler(te)
        ;
        // 全局使用的消息前缀（客户端订阅路径上会体现出来）
//        registry.setApplicationDestinationPrefixes("/app");
        // 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
        // registry.setUserDestinationPrefix("/user/");
    }
}
