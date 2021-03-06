package colin.app.service.mlife.controller;

import colin.app.service.mlife.controller.common.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
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
     * 显示主面板
     *
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "dashboard", method = RequestMethod.GET)
    public ModelAndView showDashboard(HttpSession httpSession) {
        ModelAndView dashView = new ModelAndView("dashboard");
        return dashView;
    }

    /**
     * 显示主页
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "main", method = RequestMethod.GET)
    public ModelAndView showIndex() throws Exception {
        ModelAndView mainView = new ModelAndView("main");
        return mainView;
    }

    /**
     * 显示修改头像主页
     *
     * @return
     */
    @RequestMapping(value = "admin_avatar", method = RequestMethod.GET)
    public ModelAndView showAvatar() {
        return super.returnCommonMv("admin_avatar");
    }

    /**
     * 个人资料页
     * @return
     */
    @RequestMapping(value = "admin_profile",method = RequestMethod.GET)
    public ModelAndView showProfile(){
        return super.returnCommonMv("admin_profile");
    }

    /**
     * 显示联系页
     * @return
     */
    @RequestMapping(value = "admin_contacts",method = RequestMethod.GET)
    public ModelAndView showContacts(){
        return super.returnCommonMv("admin_contacts");
    }

    /**
     * 访问用户的邮箱
     * @return
     */
    @RequestMapping(value = "admin_mailbox",method = RequestMethod.GET)
    public ModelAndView showMailbox(){
        return super.returnCommonMv("admin_mailbox");
    }

    /**
     * 用户注销
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView adminLogout(String username) {
        if (null != super.getHttpSession().getAttribute(username)) {
            super.getHttpSession().removeAttribute(username);
        }
        ModelAndView logoutView = new ModelAndView();
        logoutView.setViewName("redirect:login");
        return logoutView;
    }

}
