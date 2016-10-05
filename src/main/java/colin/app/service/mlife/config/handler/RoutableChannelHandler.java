package colin.app.service.mlife.config.handler;

/**
 * Created by Administrator on 2016/9/30.
 */
public interface RoutableChannelHandler extends ChannelHandler {

    /**
     * Returns the XMPP address. The address is used by services like the core
     * server packet router to determine if a packet should be sent to the handler.
     * Handlers that are working on behalf of the server should use the generic server
     * hostname address (e.g. server.com).
     *
     * @return the XMPP address.
     */
    public void getAddress();
}
