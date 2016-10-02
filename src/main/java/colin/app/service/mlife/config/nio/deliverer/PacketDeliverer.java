package colin.app.service.mlife.config.nio.deliverer;

import colin.app.service.mlife.config.nio.probuffer.Packet;
import colin.app.service.mlife.exception.PacketException;
import colin.app.service.mlife.exception.UnauthorizedException;

/**
 * Created by Administrator on 2016/9/30.
 */
public interface PacketDeliverer {
    /**
     * Delivers the given packet based on packet recipient and sender. The
     * deliverer defers actual routing decisions to other classes.
     * <h2>Warning</h2>
     * Be careful to enforce concurrency DbC of concurrent by synchronizing
     * any accesses to class resources.
     *
     * @param packet the packet to route
     * @throws PacketException if the packet is null or the packet could not be routed.
     */
    public void deliver(Packet packet) throws UnauthorizedException, PacketException;
}
