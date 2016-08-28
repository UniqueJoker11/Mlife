package colin.app.service.mlife.controller;

import colin.app.service.mlife.controller.common.CommonController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2016/8/27.
 */
@Controller
@Scope("request")
public class WebCrawlerParserModuleController extends CommonController{

    /**
     * 显示解析管理页面
     * @return
     */
    @RequestMapping(value = "web_parser",method = RequestMethod.GET)
    public ModelAndView showWebCrawlerParserPage(){
        ModelAndView webCrawlerParserPageMV=new ModelAndView("web_parser");
        return webCrawlerParserPageMV;
    }
}
