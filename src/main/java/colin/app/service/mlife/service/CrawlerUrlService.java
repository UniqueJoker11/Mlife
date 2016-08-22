package colin.app.service.mlife.service;

import colin.app.service.mlife.controller.wrapper.CrawlerUrlWrapper;
import colin.app.service.mlife.core.common.ReturnCommonResult;

/**
 * Created by joker on 16/8/21.
 */
public interface CrawlerUrlService {

    /**
     * 添加爬虫URL
     * @param crawlerUrlWrapper
     */
    public ReturnCommonResult addCrawlerURL(CrawlerUrlWrapper crawlerUrlWrapper);

    /**
     * 分页获取爬取链接
     * @return
     */
    public ReturnCommonResult listCrawlerURLsByPage(int pageIndex,int pageSize);
}
