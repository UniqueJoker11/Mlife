package colin.app.service.mlife.test.client;

import colin.app.service.mlife.config.nio.connection.impl.NIOConnection;
import colin.app.service.mlife.test.client.handler.ClientHandler;
import org.apache.mina.core.filterchain.DefaultIoFilterChain;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2016/9/30.
 */
public class ChatClient {
    private static int port=5222;
    public void initMinaClient(){
        IoConnector clientConnector=new NioSocketConnector();
        DefaultIoFilterChainBuilder filterChain=clientConnector.getFilterChain();
        filterChain.addLast("logger", new LoggingFilter());
        //此处不处理粘包和粘包的问题,应该自己继承CumulativeProtocolDecoder
        filterChain.addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        clientConnector.setHandler(new ClientHandler());
        ConnectFuture connectFuture=clientConnector.connect(new InetSocketAddress(port));
        IoSession session=connectFuture.getSession();
        //发送protobuf数据
        connectFuture.awaitUninterruptibly();
    }
}
