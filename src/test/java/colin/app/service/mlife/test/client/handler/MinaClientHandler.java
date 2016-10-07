package colin.app.service.mlife.test.client.handler;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

public class MinaClientHandler extends IoHandlerAdapter{

	private Logger logger=LoggerFactory.getLogger(MinaClientHandler.class);
	
	 @Override
	    public void exceptionCaught(IoSession arg0, Throwable arg1)
	            throws Exception {
	        // TODO Auto-generated method stub 
		 logger.error("错线错误",arg1);
	    }  
	  
	    /** 
	     * 当客户端接受到消息时 
	     */  
	    @Override
	    public void messageReceived(IoSession session, Object message) throws Exception {
	        //我们已设定了服务器的消息规则是一行一行读取，这里就可以转为String:  
	        String s = (String)message;
	        //Writer the received data back to remote peer  
	        System.out.println("服务端发来的收到消息: " + s);
	        //测试将消息回送给客户端  
	        //session.write(s);
	  
	    }  
	  
	    @Override
	    public void messageSent(IoSession arg0, Object arg1) throws Exception {
	        // TODO Auto-generated method stub  
			System.out.println("客户端发送成功的的消息: " + arg1);
		}
	  
	    /** 
	     * 当一个客户端被关闭时 
	     */  
	    @Override
	    public void sessionClosed(IoSession session) throws Exception {
			if (session.isActive()){
				CloseFuture closeFuture=session.closeOnFlush();
				closeFuture.addListener(new IoFutureListener<IoFuture>() {
					@Override
					public void operationComplete(IoFuture future) {
						System.out.println(future.getSession().isWriterIdle()+"==="+future.getSession().isReaderIdle());
					}
				});
				closeFuture.awaitUninterruptibly(100);
			}
			System.out.println("sessionClosed关闭客户端!");
	    }
	  
	    @Override
	    public void sessionCreated(IoSession arg0) throws Exception {
	        // TODO Auto-generated method stub
			logger.info("客户端端session被创建"+DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));
	  
	    }  
	  
	    @Override
	    public void sessionIdle(IoSession session, IdleStatus arg1) throws Exception {
	        // TODO Auto-generated method stub
			System.out.println("客户端超过空闲时间关闭");
			session.closeOnFlush();
	    }  
	  
	    /** 
	     * 当一个客户端连接进入时 
	     */  
	    @Override
	    public void sessionOpened(IoSession session) throws Exception {
	        System.out.println("incomming client:" + session.getRemoteAddress());
			/*for (int i = 0; i < 1000; i++) {
				StringBuilder msg = new StringBuilder(
						DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss") + "我是客户端发送的消息");
				msg.append(i);
				session.write(msg);
			}
			session.closeOnFlush();*/

	    }

}
