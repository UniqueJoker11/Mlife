package colin.app.service.mlife.config.handler.impl;

import colin.app.service.mlife.config.handler.StanzaHandler;
import colin.app.service.mlife.config.nio.connection.impl.NIOConnection;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/9/30.
 */
public class ClientStanzaHandler implements StanzaHandler {
    private NIOConnection connection;

    /**
     * 创建节处理器
     *
     * @param connection
     */
    @Override
    public void createStanzaHandler(NIOConnection connection) {
        this.connection=connection;
    }

    /**
     * 处理消息数据
     *
     * @param message
     */
    @Override
    public void process(Object message) {

    }
}
