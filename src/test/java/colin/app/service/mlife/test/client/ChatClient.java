package colin.app.service.mlife.test.client;

import colin.app.service.mlife.config.protobuf.codec.ProtobufCodexFactory;
import colin.app.service.mlife.core.pb.PersonPB;
import colin.app.service.mlife.test.client.handler.MinaClientHandler;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.ssl.SslFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import javax.net.ssl.SSLContext;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/9/30.
 */
public class ChatClient {
    private final static int CLIENT_PORT = 6222;

    public void initMinaClient() throws NoSuchMethodException, NoSuchAlgorithmException {
        //Create TCP/IP connection
        NioSocketConnector connector = new NioSocketConnector();

        //创建接受数据的过滤器
        DefaultIoFilterChainBuilder chain = connector.getFilterChain();
        //设定这个过滤器将一行一行(/r/n)的读取数据
        chain.addFirst("codec", new ProtocolCodecFilter(new ProtobufCodexFactory(PersonPB.Person.class)));
        //先进行安全解密
        chain.addLast("ssl", new SslFilter(SSLContext.getDefault()));
        //服务器的消息处理器：一个SamplMinaServerHander对象
        connector.setHandler(new MinaClientHandler());

        //set connect timeout
        connector.setConnectTimeout(1000);

        //连接到服务器：
        ConnectFuture cf = connector.connect(new InetSocketAddress("localhost", CLIENT_PORT));

        //等待连接创建完成
        cf.awaitUninterruptibly(2000);

        if (cf.isConnected()) {
            System.out.println("链接成功!");
            IoSession session = cf.getSession();
            if (session.isConnected()) {
                System.out.println("session连接成功");
                /*for (int i = 0; i < 2; i++) {
                    StringBuilder msg = new StringBuilder(
                            DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss") + "我是客户端发送的消息");
                    msg.append(i);
                    session.write(msg);
                }*/
                PersonPB.Person.Builder person = PersonPB.Person.newBuilder();
                person.setId(1);
                person.setName("colin");
                person.setEmail("9292@qq.com");
                session.write(person.build());
                //person.build();
                //session.write(person);
                System.out.println("给服务器发送消息完毕,开始发送结束指令");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //session.write("quit");
                //先关闭session
                try {
                    session.closeOnFlush().await(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("session连接失败");
            }
        }
    }

    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient();
        try {
            chatClient.initMinaClient();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
