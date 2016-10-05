package colin.app.service.mlife.aop;

import colin.app.service.mlife.core.bo.Response;
import colin.app.service.mlife.exception.MlifeAuthException;
import colin.app.service.mlife.exception.PacketException;
import colin.app.service.mlife.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Administrator on 2016/10/5.
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {
    private final static Logger logger= LoggerFactory.getLogger(ExceptionAdvice.class);

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnauthorizedException.class)
    public Response handleHttpMessageNotReadableException(UnauthorizedException e) {
        logger.error("参数解析失败", e);
        return new Response().failure("could_not_read_json");
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
   @ExceptionHandler(MlifeAuthException.class)
    public Response handleHttpRequestMethodNotSupportedException(UnauthorizedException e) {
        logger.error("不支持当前请求方法", e);
        return new Response().failure("request_method_not_supported");
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(PacketException.class)
    public Response handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error("不支持当前媒体类型", e);
        return new Response().failure("content_type_not_supported");
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e) {
        logger.error("服务运行异常", e);
        return new Response().failure(e.getMessage());
    }
}
