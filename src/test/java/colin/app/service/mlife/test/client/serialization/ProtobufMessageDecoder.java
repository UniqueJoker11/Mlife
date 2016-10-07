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
package colin.app.service.mlife.test.client.serialization;

import colin.app.service.mlife.core.pb.PersonPB;
import colin.app.service.mlife.core.utils.LogUtils;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
public final class ProtobufMessageDecoder<IN extends GeneratedMessageV3> extends CumulativeProtocolDecoder {

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
     * Implement this method to consume the specified cumulative buffer and
     * decode its content into message(s).
     *
     * @param session The current Session
     * @param in      the cumulative buffer
     * @param out     The {@link org.apache.mina.filter.codec.ProtocolDecoderOutput} that will receive the decoded message
     * @return <tt>true</tt> if and only if there's more to decode in the buffer
     * and you want to have <tt>doDecode</tt> method invoked again.
     * Return <tt>false</tt> if remaining data is not enough to decode,
     * then this method will be invoked again when more data is
     * cumulated.
     * @throws Exception if cannot decode <tt>in</tt>.
     */
    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        if(null==in||in.remaining()<4){
           return false;
        }else{
            // 标记开始位置，如果一条消息没传输完成则返回到这个位置
            in.mark();
            // 读取header部分，获取body长度
            int msgLength=in.getInt();
            // 如果body没有接收完整，直接返回false
            if(in.remaining()<msgLength){
                return false;
            }else{
                byte[] msgBytes=new byte[msgLength];
                in.get(msgBytes);
                PersonPB.Person person=PersonPB.Person.parseFrom(msgBytes);
                session.write(person);
                return true;
            }
        }
    }

}
