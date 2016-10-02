package colin.app.service.mlife.config.nio.fragment;

/**
 * Created by Administrator on 2016/9/30.
 */
public interface StreamID {
    /**
     * Obtain a unique identifier for easily identifying this stream in
     * a database.
     *
     * @return The unique ID for this stream
     */
    public String getID();
}
