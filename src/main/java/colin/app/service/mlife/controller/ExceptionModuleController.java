package colin.app.service.mlife.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by joker on 16/8/20.
 */
@Controller
@Scope("request")
@RequestMapping("error")
public class ExceptionModuleController {
    /**
     * 显示错误页面
     * @return
     */
    @RequestMapping(value = "ie",method = RequestMethod.GET)
    public String showError(){
        return "error/ie";
    }
}
