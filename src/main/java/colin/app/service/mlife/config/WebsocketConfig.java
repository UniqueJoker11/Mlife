package colin.app.service.mlife.config;

import colin.app.service.mlife.config.handler.WebChatHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by Administrator on 2016/9/20.
 */
@Configuration
public class WebsocketConfig implements WebSocketConfigurer {
    private Logger logger= LoggerFactory.getLogger(WebsocketConfig.class);
    @Autowired
    private WebChatHandler webChatHandler;
    /**
     * 注册处理websocket的handler类
     * @param webSocketHandlerRegistry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        //注册Handler
        webSocketHandlerRegistry.addHandler(webChatHandler,"/ws/chat").setAllowedOrigins("*").withSockJS();
    }
}
