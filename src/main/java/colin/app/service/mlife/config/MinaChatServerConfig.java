package colin.app.service.mlife.config;

import colin.app.service.mlife.chat.handler.ConnectionHandler;
import colin.app.service.mlife.core.prop.MinaProps;
import org.apache.mina.core.filterchain.DefaultIoFilterChain;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by joker on 16/9/15.
 */
@Configuration
public class MinaChatServerConfig {

    private final Logger logger = LoggerFactory.getLogger(MinaChatServerConfig.class);

    @Autowired
    private MinaProps minaProps;

    @Autowired
    private ConnectionHandler connectionHandler;
    /**
     * TCP 服务器
     * @return
     * @throws IOException
     */
    @Bean
    public NioSocketAcceptor initMinaChatTCPServer() throws IOException {
        NioSocketAcceptor minaChatTcpServer = new NioSocketAcceptor();
        //配置基础的过滤链
        DefaultIoFilterChainBuilder tcpFilterChain = minaChatTcpServer.getFilterChain();
        tcpFilterChain.addLast("logger", new LoggingFilter());
        //此处不处理粘包和粘包的问题,应该自己继承CumulativeProtocolDecoder
        tcpFilterChain.addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        //设定处理器
        minaChatTcpServer.setHandler(connectionHandler);
        //设置session缓冲区大小
        minaChatTcpServer.getSessionConfig().setReadBufferSize(minaProps.getBufferSize());
        //设置session的空闲时间,空闲时间达到设定值session关闭
        /**
         * 在对 setIdleTime 的调用中，第一个参数定义了再断定 session 是否闲置时要检查的行为，
         * 第二个参数定义了在 session 被视为空闲之前以毫秒为单位的时间长度内必须发生。
         * 10毫秒内发生读写空闲行为处罚sessionIdle方法
         */
        minaChatTcpServer.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        //设定监听的tcp端口号
        minaChatTcpServer.bind(new InetSocketAddress(minaProps.getTcpPort()));
        logger.info("啟動mina成功,坚挺的TCP端口是是:"+minaProps.getTcpPort());
        return minaChatTcpServer;
    }


    /**
     * UDP服务器
     * @return
     */
    @Bean
    public NioDatagramAcceptor initMinaChatUDPServer(){
        NioDatagramAcceptor minaChatUdpServer=new NioDatagramAcceptor();
        DefaultIoFilterChainBuilder udpFilterChain=minaChatUdpServer.getFilterChain();
        udpFilterChain.addLast("logger",new LoggingFilter());
        return minaChatUdpServer;
    }


}
