package colin.app.service.mlife.config.handler;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by joker on 16/9/15.
 */
public class IOConnectionHandler implements IoHandler {
    private final static Logger logger = LoggerFactory.getLogger(IOConnectionHandler.class);

    private final List<String> msgList=new CopyOnWriteArrayList<String>();
    /**
     * Invoked from an I/O processor thread when a new connection has been created.
     * Because this method is supposed to be called from the same thread that
     * handles I/O of multiple sessions, please implement this method to perform
     * tasks that consumes minimal amount of time such as socket parameter
     * and user-defined session attribute initialization.
     *
     * @param session The session being created
     * @throws Exception If we get an exception while processing the create event
     */
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        String userId = UUID.randomUUID().toString();
        session.setAttribute("userId", userId);
        logger.info("用户的Session被创建了其对应的userId是" + userId);
    }

    /**
     * Invoked when a connection has been opened.  This method is invoked after
     * {@link #sessionCreated(IoSession)}.  The biggest difference from
     * {@link #sessionCreated(IoSession)} is that it's invoked from other thread
     * than an I/O processor thread once thread model is configured properly.
     *
     * @param session The session being opened
     * @throws Exception If we get an exception while processing the open event
     */
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        logger.info("用户的Session被打开了,当前userId是" + session.getAttribute("userId"));
    }

    /**
     * Invoked when a connection is closed.
     *
     * @param session The session being closed
     * @throws Exception If we get an exception while processing the close event
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        logger.info("用户的Session被关闭了,当前userId是" + session.getAttribute("userId")+"===接收到的消息数量是"+msgList.size());
        session.closeOnFlush();
    }

    /**
     * Invoked with the related {@link IdleStatus} when a connection becomes idle.
     * This method is not invoked if the transport type is UDP; it's a known bug,
     * and will be fixed in 2.0.
     *
     * @param session The idling session
     * @param status  The session's status
     * @throws Exception If we get an exception while processing the idle event
     */
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        logger.info("用户的Session处于空闲期,当前userId是" + session.getAttribute("userId") + ",空闲状态是" + status.toString());
       CloseFuture closeFuture= session.closeOnFlush();
//        closeFuture.awaitUninterruptibly();
    }

    /**
     * Invoked when any exception is thrown by user {@link IoHandler}
     * implementation or by MINA.  If <code>cause</code> is an instance of
     * {@link IOException}, MINA will close the connection automatically.
     *
     * @param session The session for which we have got an exception
     * @param cause   The exception that has been caught
     * @throws Exception If we get an exception while processing the caught exception
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        logger.error("用户的Session出现异常,当前userId是" + session.getAttribute("userId"), cause);
        cause.printStackTrace();
        session.closeOnFlush();
    }

    /**
     * Invoked when a message is received.
     *
     * @param session The session that is receiving a message
     * @param message The received message
     * @throws Exception If we get an exception while processing the received message
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        logger.info("服务端收到了消息,当前userId是" + session.getAttribute("userId")+"收到的消息内容是" + message.toString());
        String msg=(String)message;
        msgList.add(msg);
        String responseMsg = "服务端已收到客户端发送的第"+msgList.size() + "条消息,我是服务端返回的消息" + DateFormatUtils.format(Calendar.getInstance(),"yyyy-MM-dd HH:mm:ss");
        session.write(responseMsg);
        if( msg.trim().equalsIgnoreCase("quit") ) {
            System.out.println("接收到了关闭指令,服务端session关闭");
            if(session.isConnected()){
                session.getCloseFuture().awaitUninterruptibly();
            }
            return;
        }

    }

    /**
     * Invoked when a message written by {@link IoSession#write(Object)} is
     * sent out.
     *
     * @param session The session that has sent a full message
     * @param message The sent message
     * @throws Exception If we get an exception while processing the sent message
     */
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        //String msgContent = new String(((IoBuffer) message).array(), Charset.forName("UTF-8"));
        logger.info("服务器返回的消息内容是" + message);
    }

    /**
     * Handle the closure of an half-duplex TCP channel
     *
     * @param session The session which input is being closed
     * @throws Exception If we get an exception while closing the input
     */
    @Override
    public void inputClosed(IoSession session) throws Exception {
        //logger.info("inputClosed方法被调用");
       CloseFuture closeFuture= session.closeOnFlush();
        if (closeFuture.isClosed()){
            closeFuture.awaitUninterruptibly();
        }
    }
}
