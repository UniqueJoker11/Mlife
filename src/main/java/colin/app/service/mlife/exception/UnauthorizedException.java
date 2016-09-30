package colin.app.service.mlife.exception;

/**
 * Created by Administrator on 2016/9/30.
 */
public class UnauthorizedException extends Exception {
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super("用户认证失败");
    }
}
