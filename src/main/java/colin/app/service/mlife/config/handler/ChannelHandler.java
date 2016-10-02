package colin.app.service.mlife.config.handler;

import colin.app.service.mlife.config.nio.probuffer.Packet;
import colin.app.service.mlife.exception.PacketException;
import colin.app.service.mlife.exception.UnauthorizedException;

/**
 * Created by Administrator on 2016/9/30.
 */
public interface ChannelHandler <T extends Packet> {

    /**
     * Process an XMPP packet.
     *
     * @param packet a packet to process.
     * @throws UnauthorizedException if not allowed to process the packet.
     * @throws PacketException thrown if the packet is malformed (results in the sender's
     *      session being shutdown).
     */
    public abstract void process(T packet) throws UnauthorizedException, PacketException;
}
