package colin.app.service.mlife.config.listener;

/**
 * Created by Administrator on 2016/9/30.
 */
public interface ConnectionCloseListener {
    /**
     * Called when a connection is closed.
     *
     * @param handback The handback object associated with the connection listener during Connection.registerCloseListener()
     */
    public void onConnectionClose(Object handback);
}
