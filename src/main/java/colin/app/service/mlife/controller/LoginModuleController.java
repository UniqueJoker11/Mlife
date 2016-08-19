package colin.app.service.mlife.controller;

import colin.app.service.mlife.controller.common.CommonController;
import colin.app.service.mlife.core.common.ReturnCommonResult;
import colin.app.service.mlife.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/8/18.
 */
@Controller
@Scope("request")
public class LoginModuleController extends CommonController{
    @Autowired
    UserService userService;

    /**
     * 用户登录页
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String showLogin(){
        return "login";
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ModelAndView adminLogin(String username,String password,HttpSession httpSession){
        ModelAndView adminView=new ModelAndView();
        ReturnCommonResult result=userService.isUserExists(username);
        if(!result.isSuccess()){
            adminView.setViewName("redirect:login");
        }else{
            result=userService.authUserInfo(username,password);
            if (result.isSuccess()){
                super.getHttpSession().setAttribute(username,result.getData());
                //设置用户给cookie,未加密有安全隐患
                Cookie userCookie=new Cookie("user", username);
                userCookie.setMaxAge(-1);
                super.getResponse().addCookie(userCookie);
                adminView.setViewName("redirect:dashboard");
            }else{
                adminView.setViewName("redirect:login");
            }
        }
      return adminView;
    }

    /**
     * 用户注销
     * @param username
     * @return
     */
    @RequestMapping(value = "logout",method = RequestMethod.POST)
    public ModelAndView adminLogout(String username){
        if(null!=super.getHttpSession().getAttribute(username)){
            super.getHttpSession().removeAttribute(username);
        }
        ModelAndView logoutView=new ModelAndView();
        logoutView.setViewName("redirect:login");
        return logoutView;
    }

}
