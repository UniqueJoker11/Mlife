package colin.app.service.mlife.config.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2016/9/20.
 */
@Component
public class WebChatManager {
    private Logger logger = LoggerFactory.getLogger(WebChatManager.class);
    private Map<String, WebSocketSession> chatSessionManager = new ConcurrentHashMap<String, WebSocketSession>();

    /**
     * 初始化session
     * @param username
     * @param session
     */
    public void initUserSession(String username, WebSocketSession session) {
        if (!isValidaParams(username, session)) {
            logger.error("因为参数校验不合法，初始化session失败");
            return;
        }
        if (!chatSessionManager.containsKey(username)) {
            chatSessionManager.put(username, session);
        }
    }

    /**
     * 添加用户session
     * @param username
     * @param session
     */
    public void addUserSession(String username, WebSocketSession session) {
        if (!isValidaParams(username, session)) {
            logger.error("因为参数校验不合法，添加session失败");
            return;
        }
        chatSessionManager.put(username, session);
    }

    /**
     * 删除用户session
     * @param username
     */
    public void delUserSession(String username){
        if(StringUtils.isEmpty(username)){
            logger.warn("由于username为空，删除session失败！");
            return;
        }
        if(chatSessionManager.containsKey(username)){
            chatSessionManager.remove(username);
        }
    }

    /**
     * 获取用户的session
     * @param username
     * @return
     */
    public WebSocketSession getUserSession(String username){
        if(StringUtils.isEmpty(username)){
            logger.warn("由于username为空，获取session失败！");
            return null;
        }
        return chatSessionManager.get(username);
    }
    public List<WebSocketSession> getAllOnlineUserSession(String username){
        if(chatSessionManager.isEmpty()){
            return null;
        }
        List<WebSocketSession> webSocketSessionList=new LinkedList<WebSocketSession>();
        Set<String> userKeySet=chatSessionManager.keySet();
        for(String userKey:userKeySet){
            if(!userKey.equals(username)){
                webSocketSessionList.add(chatSessionManager.get(userKey));
            }
        }
        return webSocketSessionList;
    }

    /**
     * 判断参数的有效性
     *
     * @param username
     * @param session
     * @return
     */
    private boolean isValidaParams(String username, WebSocketSession session) {
        if (StringUtils.isEmpty(username) || ObjectUtils.isEmpty(session)) {
            logger.error("参数不合法，username：" + StringUtils.isEmpty(username) + "|session：" + ObjectUtils.isEmpty(session));
            return false;
        } else {
            return true;
        }
    }
}
