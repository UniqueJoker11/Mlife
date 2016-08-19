package colin.app.service.mlife.core.common;

/**
 * Created by Administrator on 2016/8/18.
 */
public class ReturnCommonResult {

    private boolean success;
    private String msg;
    private Object data;

    public ReturnCommonResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public ReturnCommonResult(boolean success) {
        this.success = success;
    }

    public ReturnCommonResult(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public ReturnCommonResult(boolean success, Object data, String msg) {
        this.success = success;
        this.data = data;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
