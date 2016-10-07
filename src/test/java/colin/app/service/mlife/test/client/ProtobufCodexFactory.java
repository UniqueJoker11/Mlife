package colin.app.service.mlife.test.client;

import colin.app.service.mlife.config.protobuf.codec.serialization.ProtobufMessageDecoder;
import colin.app.service.mlife.config.protobuf.codec.serialization.ProtobufMessageEncoder;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * Created by Administrator on 2016/10/5.
 */
public class ProtobufCodexFactory implements ProtocolCodecFactory {

    private final ProtobufMessageEncoder protobufEncoder;

    private final ProtobufMessageDecoder protobufDecoder;

    public ProtobufCodexFactory(Class<? extends GeneratedMessageV3> clazz) throws NoSuchMethodException {
        protobufEncoder = ProtobufMessageEncoder.newInstance(clazz);
        protobufDecoder = ProtobufMessageDecoder.newInstance(clazz);
    }

    public ProtobufCodexFactory(Class<? extends GeneratedMessageV3> clazz, ExtensionRegistryLite registry) throws NoSuchMethodException {
        protobufEncoder = ProtobufMessageEncoder.newInstance(clazz);
        protobufDecoder = ProtobufMessageDecoder.newInstance(clazz,registry);
    }

    /**
     * Returns a new (or reusable) instance of {@link org.apache.mina.filter.codec.ProtocolEncoder} which
     * encodes message objects into binary or protocol-specific data.
     *
     * @param session The current session
     * @return The encoder instance
     * @throws Exception If an error occurred while retrieving the encoder
     */
    @Override
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return protobufEncoder;
    }

    /**
     * Returns a new (or reusable) instance of {@link org.apache.mina.filter.codec.ProtocolDecoder} which
     * decodes binary or protocol-specific data into message objects.
     *
     * @param session The current session
     * @return The decoder instance
     * @throws Exception If an error occurred while retrieving the decoder
     */
    @Override
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return protobufDecoder;
    }

}
