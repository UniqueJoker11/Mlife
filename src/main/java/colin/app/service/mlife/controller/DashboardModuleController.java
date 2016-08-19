package colin.app.service.mlife.controller;

import colin.app.service.mlife.controller.common.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/8/19.
 */
@Controller
public class DashboardModuleController extends CommonController {

    /**
     * 显示首页
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "index",method = RequestMethod.GET )
    public ModelAndView showDashboard(HttpSession httpSession){
        ModelAndView dashView=new ModelAndView();
        if(null==super.getRequest().getParameter("user")){
            dashView.setViewName("redirect:login");
        }else {
            String username=super.getRequest().getParameter("user");
            if(null== super.getHttpSession().getAttribute(username)) {
                dashView.setViewName("redirect:login");
            }else{
                dashView.setViewName("index");
            }
        }
        return dashView;
    }
}
