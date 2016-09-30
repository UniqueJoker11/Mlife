package colin.app.service.mlife.exception;

/**
 * Created by Administrator on 2016/9/30.
 */
public class PacketException extends Exception{
    public PacketException(String msg){
        super(msg);
    }
    public PacketException(){
        super("节数据处理错误");
    }
}
