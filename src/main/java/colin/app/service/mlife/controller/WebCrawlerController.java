package colin.app.service.mlife.controller;

import colin.app.service.mlife.controller.common.CommonController;
import colin.app.service.mlife.controller.wrapper.CrawlerUrlWrapper;
import colin.app.service.mlife.core.common.ReturnCommonResult;
import colin.app.service.mlife.service.CrawlerUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by joker on 16/8/19.
 */
@Controller
@Scope("request")
public class WebCrawlerController extends CommonController {

    @Autowired
    private CrawlerUrlService crawlerUrlService;

    /**
     * 显示爬虫配置页面
     *
     * @return
     */
    @RequestMapping(value = "web_crawler", method = RequestMethod.GET)
    public ModelAndView showWebCrawler() {
        ModelAndView crawlerView = new ModelAndView();
        crawlerView.setViewName("web_crawler");
        return crawlerView;
    }

    /**
     * 获取爬取URL
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "list_web_crawler",method = RequestMethod.GET)
    @ResponseBody
    public Object listCrawlerUrlInfo(@RequestParam int pageIndex,@RequestParam int pageSize){
        ReturnCommonResult result=crawlerUrlService.listCrawlerURLsByPage(pageIndex,pageSize);
        if(result.isSuccess()){
            return result.getData();
        }else {
            return null;
        }
    }
    /**
     * 添加爬取URL
     * @param crawlerUrlWrapper
     * @return
     */
    @RequestMapping(value = "add_crawler_url", method = RequestMethod.PUT,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object addCrawlerUrlInfo(@RequestBody CrawlerUrlWrapper crawlerUrlWrapper) {
        ReturnCommonResult result=null;
        if(null==crawlerUrlWrapper){
            result=new ReturnCommonResult(false);
        }else{
            result=crawlerUrlService.addCrawlerURL(crawlerUrlWrapper);
        }
        return result;
    }

}
