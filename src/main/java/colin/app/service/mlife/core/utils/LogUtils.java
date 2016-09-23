package colin.app.service.mlife.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;

/**
 * Created by Administrator on 2016/9/23.
 */
@Component
public class LogUtils {
    private Logger logger=LoggerFactory.getLogger(LogUtils.class);

  /*  private Class<?> clazz;

    @PostConstruct
    public void initLoginUtils(Class<?> clazz){
        this.clazz=clazz;
    }*/
    /**
     *
     * @param clazz
     * @param msg
     */
    public void info(Class<?> clazz,String msg){
        logger=LoggerFactory.getLogger(clazz);
        logger.info(msg);
    }

    /**
     *
     * @param clazz
     * @param msg
     */
    public void warn(Class<?> clazz,String msg){
        logger=LoggerFactory.getLogger(clazz);
        logger.warn(msg);
    }

    /**
     *
     * @param clazz
     * @param msg
     * @param e
     */
    public void error(Class<?> clazz,String msg,Exception e){
        logger=LoggerFactory.getLogger(clazz);
        if(ObjectUtils.isEmpty(e)){
            logger.error(msg);
        }else{
            logger.error(msg,e);
        }
    }
}
