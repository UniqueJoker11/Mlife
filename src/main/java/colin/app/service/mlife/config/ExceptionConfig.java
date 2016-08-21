package colin.app.service.mlife.config;

import colin.app.service.mlife.exception.MlifeAuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by joker on 16/8/19.
 */
@Configuration
public class ExceptionConfig implements HandlerExceptionResolver {
    private final static Logger log= LoggerFactory.getLogger(ExceptionConfig.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        ModelAndView errorView=new ModelAndView();
        log.error("系统报错了,{}",e);
        if(e instanceof Exception){
            errorView.setViewName("error/500");
        }else if(e instanceof MlifeAuthException){
            errorView.setViewName("error/500");
            errorView.addObject("msg","未注册的用户不准登录");
        }
        return errorView;
    }
}
