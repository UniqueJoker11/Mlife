package colin.app.service.mlife.controller;

import colin.app.service.mlife.controller.common.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2016/8/31.
 */
@Controller
public class ForumModuleController extends CommonController {

    @RequestMapping(value = "forum",method = RequestMethod.GET)
    public ModelAndView showForum() {
        return super.returnCommonMv("admin_forum");
    }
}
