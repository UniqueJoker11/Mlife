package colin.app.service.mlife.config.nio.connection.impl;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/9/30.
 */

public class NIOConnection {
    private IoSession session;

    public IoSession getSession() {
        return session;
    }

    public void setSession(IoSession session) {
        this.session = session;
    }
}
