package colin.app.service.mlife.controller;

import colin.app.service.mlife.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2016/8/18.
 */
@RestController
public class LoginModuleController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public ModelAndView addUserInfo(){
return null;
    }
}
