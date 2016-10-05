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

import colin.app.service.mlife.core.pb.PersonPB;
import colin.app.service.mlife.core.utils.LogUtils;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
public final class ProtobufMessageDecoder<IN extends GeneratedMessageV3> implements ProtocolDecoder {

    private final Class <? extends GeneratedMessageV3> protobufClazz;

    private final Method parseMethod;

    private final ExtensionRegistryLite registry;

    public static <TYPE extends GeneratedMessageV3> ProtobufMessageDecoder<TYPE> newInstance(Class<TYPE> c)
            throws NoSuchMethodException {
        return newInstance(c, ExtensionRegistryLite.getEmptyRegistry());
    }

    public static <TYPE extends GeneratedMessageV3> ProtobufMessageDecoder<TYPE> newInstance(Class<TYPE> c,
            ExtensionRegistryLite registry) throws NoSuchMethodException {
        return new ProtobufMessageDecoder<TYPE>(c, registry);
    }

    private ProtobufMessageDecoder(Class<IN> protobufClazz, ExtensionRegistryLite registry) throws NoSuchMethodException {
        super();
        this.protobufClazz = protobufClazz;
        parseMethod = protobufClazz.getDeclaredMethod("parseFrom", InputStream.class, ExtensionRegistryLite.class);
        this.registry = registry;
    }

    /**
     * Decodes binary or protocol-specific content into higher-level message objects.
     * MINA invokes {@link #decode(org.apache.mina.core.session.IoSession, org.apache.mina.core.buffer.IoBuffer, org.apache.mina.filter.codec.ProtocolDecoderOutput)}
     * method with read data, and then the decoder implementation puts decoded
     * messages into {@link org.apache.mina.filter.codec.ProtocolDecoderOutput}.
     *
     * @param session The current Session
     * @param in      the buffer to decode
     * @param out     The {@link org.apache.mina.filter.codec.ProtocolDecoderOutput} that will receive the decoded message
     * @throws Exception if the read data violated protocol specification
     */
    @Override
    public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        String protobufName=protobufClazz.getName();
        LogUtils.info(ProtobufMessageDecoder.class,"消息协议名称是"+protobufName);
        if(protobufName.contains("PersonPB$Person")){
            PersonPB.Person person= PersonPB.Person.parseFrom(in.asInputStream());
            out.write(person);
        }else{
            LogUtils.warn(ProtobufMessageDecoder.class,"无法处理的消息类型");
            out.write(in);
        }
    }

    /**
     * Invoked when the specified <tt>session</tt> is closed.  This method is useful
     * when you deal with the protocol which doesn't specify the length of a message
     * such as HTTP response without <tt>content-length</tt> header. Implement this
     * method to process the remaining data that {@link #decode(org.apache.mina.core.session.IoSession, org.apache.mina.core.buffer.IoBuffer, org.apache.mina.filter.codec.ProtocolDecoderOutput)}
     * method didn't process completely.
     *
     * @param session The current Session
     * @param out     The {@link org.apache.mina.filter.codec.ProtocolDecoderOutput} that contains the decoded message
     * @throws Exception if the read data violated protocol specification
     */
    @Override
    public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
          LogUtils.info(ProtobufMessageDecoder.class,"结束解码！");
    }

    /**
     * Releases all resources related with this decoder.
     *
     * @param session The current Session
     * @throws Exception if failed to dispose all resources
     */
    @Override
    public void dispose(IoSession session) throws Exception {
         LogUtils.info(ProtobufMessageDecoder.class,"释放解密资源");
    }
}
