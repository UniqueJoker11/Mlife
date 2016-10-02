package colin.app.service.mlife.exception;

/**
 * Created by Administrator on 2016/9/30.
 */
public class UnknownHostException extends Exception{
    public UnknownHostException(String msg){
        super(msg);
    }
    public UnknownHostException(){
        super("无法识别的主机名称");
    }
}
