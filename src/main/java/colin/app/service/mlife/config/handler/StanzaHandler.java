package colin.app.service.mlife.config.handler;

import colin.app.service.mlife.config.nio.connection.impl.NIOConnection;

/**
 * Created by Administrator on 2016/9/30.
 */
public interface StanzaHandler {

    /**
     * 创建节处理器
     * @param connection
     */
    public void createStanzaHandler(NIOConnection connection);

    /**
     * 处理消息数据
     * @param message
     */
    public void process(Object message);
}
