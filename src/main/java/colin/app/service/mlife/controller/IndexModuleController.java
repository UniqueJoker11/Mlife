package colin.app.service.mlife.controller;

import colin.app.service.mlife.controller.common.CommonAsyncController;
import colin.app.service.mlife.core.common.SystemConstants;
import colin.app.service.mlife.core.utils.MLifeDigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2016/8/30.
 */
@Controller
@Scope("request")
public class IndexModuleController extends CommonAsyncController{

    private final static Logger logger= LoggerFactory.getLogger(IndexModuleController.class);
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public WebAsyncTask<ModelAndView> showMlifePage() {
        Callable<ModelAndView> callable = new Callable<ModelAndView>() {
            @Override
            public ModelAndView call() throws Exception {
                ModelAndView forumPageMV = new ModelAndView("forum_index");
                return forumPageMV;
            }
        };
        WebAsyncTask<ModelAndView> mLifeForumPage = new WebAsyncTask<ModelAndView>(1000, callable);
        return mLifeForumPage;
    }

    /**
     * 微信接口验证程序
     * AppID(应用ID)wxb37f653a51018047
     * AppSecret(应用密钥)b830afb23310ebfbb48e7a88cb53c9ed
     * Token 13RTYHJ456FGHJ67FGHMrtVBNM452DFGBN
     * EncodingAESKey eH6MW1IEgAtESQMwqZitIW6ZOcjSXXsAai7CyNORWek
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @RequestMapping(value = "/weichat",method = RequestMethod.GET)
    @ResponseBody
    public WebAsyncTask<Object> weiChatValidate(@RequestParam String signature,@RequestParam String timestamp,@RequestParam String nonce,@RequestParam String echostr){
        logger.info("获取到的请求随机字符串是"+echostr);
        if(this.isRequestFromWeiChat(signature,timestamp,nonce)){
            return super.callableAjaxObj(echostr);
        }else{
            return null;
        }

    }

    /**
     * 判断请求是否是微信的
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    private boolean isRequestFromWeiChat(String signature,String timestamp,String nonce){
        StringBuilder encodeStr=new StringBuilder(SystemConstants.WCHAT_TOKEN);
        encodeStr.append(timestamp).append(nonce);
        String encodeData=MLifeDigestUtils.encode(encodeStr.toString(),"SHA1");
        logger.info("加密完的数据是"+encodeData);
        if(signature.equals(encodeData)){
            return true;
        }else {
            return false;
        }
    }
}
