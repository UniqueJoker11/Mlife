package colin.app.service.mlife.controller;

import colin.app.service.mlife.controller.common.CommonController;
import colin.app.service.mlife.controller.wrapper.CrawlerUrlWrapper;
import colin.app.service.mlife.controller.wrapper.DataTableResultWrapper;
import colin.app.service.mlife.core.common.ReturnCommonResult;
import colin.app.service.mlife.core.pojo.CrawlerURL;
import colin.app.service.mlife.service.CrawlerUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by joker on 16/8/19.
 */
@Controller
@Scope("request")
public class WebCrawlerController extends CommonController {

    @Autowired
    private CrawlerUrlService crawlerUrlService;

    private static final AtomicLong requestTimes=new AtomicLong(0);
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
    @RequestMapping(value = "list_web_crawler",method = RequestMethod.POST)
    @ResponseBody
    public Object listCrawlerUrlInfo(@RequestParam int start,@RequestParam int length){
        ReturnCommonResult result=crawlerUrlService.listCrawlerURLsByPage(start/length+1,length);
        if(result.isSuccess()){
            DataTableResultWrapper dataTableResultWrapper=new DataTableResultWrapper();
           Map<String,Object> resultMap= ( Map<String,Object>) result.getData();
            List<CrawlerURL> crawlerURLs=(List<CrawlerURL>)resultMap.get("data");
            Long totalSize=(Long)resultMap.get("total");
            dataTableResultWrapper.setData(crawlerURLs);
            dataTableResultWrapper.setDraw(requestTimes.addAndGet(1));
            dataTableResultWrapper.setRecordsTotal(totalSize);
            dataTableResultWrapper.setRecordsFiltered(totalSize);
            return dataTableResultWrapper;
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
