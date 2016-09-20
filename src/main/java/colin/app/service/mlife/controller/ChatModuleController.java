package colin.app.service.mlife.controller;

import colin.app.service.mlife.controller.common.CommonController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2016/9/20.
 */
@Controller
@Scope("request")
public class ChatModuleController extends CommonController{
    @RequestMapping(value = "/chat",method = RequestMethod.GET)
    public ModelAndView showChatPage(){
        return super.returnCommonMv("chat");
    }
}
