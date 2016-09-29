package colin.app.service.mlife.config.handler;

import colin.app.service.mlife.config.manager.WebChatManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/9/20.
 */
@Component
public class WebChatHandler extends AbstractWebSocketHandler {
    private Logger logger= LoggerFactory.getLogger(WebChatHandler.class);

    @Autowired
    private WebChatManager webChatManager;


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String queryStr=session.getUri().getQuery();
        if (StringUtils.isEmpty(queryStr)){
            sendTextMsg("聊天用户为空无法聊天",session);
            session.close();
            return;
        }
        String username=queryStr.split("=")[1];
        webChatManager.addUserSession(username, session);
        logger.info("websocket连接稳定"+session.getId()+"==="+session.getAttributes().isEmpty());
    }
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("处理收到的文本消息："+message+"==="+session.getId());
        String username=message.getPayload().split(":")[0];
        broadcastMsg(message,webChatManager.getAllOnlineUserSession(username));
    }
    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        logger.info("处理收到的二进制消息："+message+"==="+session.getId());
    }
    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        logger.info("处理收到的pong消息："+message+"==="+session.getId());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        logger.error("处理收到的异常消息：",exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("客户端连接断开:"+status+"==="+session.getId());
    }

    /**
     * 广播发送消息
     * @param msg
     * @param webSocketSessions
     */
    private void broadcastMsg(String msg,List<WebSocketSession> webSocketSessions){
        for(WebSocketSession  userSession:webSocketSessions){
            sendTextMsg(msg,userSession);
        }
    }
    /**
     * 广播发送消息
     * @param msg
     * @param webSocketSessions
     */
    private void broadcastMsg(TextMessage msg,List<WebSocketSession> webSocketSessions){
        for(WebSocketSession  userSession:webSocketSessions){
            sendTextMsg(msg,userSession);
        }
    }
    /**
     *
     * @param msg
     * @param session
     */
    private void sendTextMsg(String msg,WebSocketSession session){
        TextMessage responseText=new TextMessage(msg);
        try {
            session.sendMessage(responseText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * @param msg
     * @param session
     */
    private void sendTextMsg(TextMessage msg,WebSocketSession session){
        try {
            session.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}