/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package colin.app.service.mlife.config.protobuf.codec.serialization;


import com.google.protobuf.GeneratedMessageV3;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
public class ProtobufMessageEncoder implements ProtocolEncoder {
    private final Class<? extends GeneratedMessageV3> protobufClazz;

    public ProtobufMessageEncoder(Class<? extends GeneratedMessageV3> protobufClazz) {
        this.protobufClazz = protobufClazz;
    }

    public static <T extends GeneratedMessageV3> ProtobufMessageEncoder newInstance(Class<T> protobufClazz) {
        return new ProtobufMessageEncoder(protobufClazz);
    }


    /**
     * Encodes higher-level message objects into binary or protocol-specific data.
     * MINA invokes {@link #encode(org.apache.mina.core.session.IoSession, Object, org.apache.mina.filter.codec.ProtocolEncoderOutput)}
     * method with message which is popped from the session write queue, and then
     * the encoder implementation puts encoded messages (typically {@link org.apache.mina.core.buffer.IoBuffer}s)
     * into {@link org.apache.mina.filter.codec.ProtocolEncoderOutput}.
     *
     * @param session The current Session
     * @param message the message to encode
     * @param out     The {@link org.apache.mina.filter.codec.ProtocolEncoderOutput} that will receive the encoded message
     * @throws Exception if the message violated protocol specification
     */
    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        if(null!=message){
            out.write(message);
        }
    }

    /**
     * Releases all resources related with this encoder.
     *
     * @param session The current Session
     * @throws Exception if failed to dispose all resources
     */
    @Override
    public void dispose(IoSession session) throws Exception {

    }
}