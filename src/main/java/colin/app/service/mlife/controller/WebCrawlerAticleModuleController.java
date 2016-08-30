package colin.app.service.mlife.controller;

import colin.app.service.mlife.controller.common.CommonController;
import colin.app.service.mlife.core.common.ReturnCommonResult;
import colin.app.service.mlife.service.CrawlerAticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2016/8/27.
 */
@Controller
@Scope("request")
public class WebCrawlerAticleModuleController extends CommonController {

    @Autowired
    private CrawlerAticleService crawlerAticleService;

    /**
     * 显示文章列表页
     * @return
     */
    @RequestMapping(value = "crawler_aticle",method = RequestMethod.GET)
    public ModelAndView showCrawlerAticlePage(){
        ModelAndView CrawlerAticlePageMV=new ModelAndView("web_aticle");
        return CrawlerAticlePageMV;
    }

    /**
     * 显示文章详情页
     * @return
     */
    @RequestMapping(value = "crawler_aticle_detail",method = RequestMethod.GET)
    public ModelAndView showCrawlerAtcileDetailPage(@RequestParam String aid){
        ModelAndView crawlerAticleDetailPageMv=new ModelAndView();
        ReturnCommonResult result=crawlerAticleService.findCrawlerAticleDetail(aid);
        if(!result.isSuccess()){
            crawlerAticleDetailPageMv.setViewName("error/500");
        }else{
            crawlerAticleDetailPageMv.setViewName("web_aticle_detail");
            crawlerAticleDetailPageMv.addObject("aticle",result.getData());
        }
        return crawlerAticleDetailPageMv;
    }
    /**
     * 获取爬取文章列表
     *
     * @param pageIndex
     * @param dataSize
     * @return
     */
    @RequestMapping(value = "crawler_aticle_list",method = RequestMethod.POST)
    public ModelAndView showCrawlerAticleDigestList(@RequestParam int pageIndex, @RequestParam int dataSize) {
        ReturnCommonResult commonResult = crawlerAticleService.findCrawlerAticleDigestByPage(pageIndex, dataSize);
        ModelAndView crawlerAticleDigestMV = new ModelAndView();
        if (pageIndex == 0 && !commonResult.isSuccess()) {
            crawlerAticleDigestMV.addObject("msg", "当前还没有文章！");
            crawlerAticleDigestMV.setViewName("error/tips");
        } else if (pageIndex > 0 && !commonResult.isSuccess()) {
            crawlerAticleDigestMV.addObject("msg", "已经是最后一页了!");
            crawlerAticleDigestMV.setViewName("error/tips");
        } else {
            crawlerAticleDigestMV.addObject("aticlesInfo", commonResult.getData());
            crawlerAticleDigestMV.setViewName("fragment/web_aticle_list");
        }
        return crawlerAticleDigestMV;
    }

}
