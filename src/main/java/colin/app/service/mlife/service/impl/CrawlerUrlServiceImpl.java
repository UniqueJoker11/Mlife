package colin.app.service.mlife.service.impl;

import colin.app.service.mlife.controller.wrapper.CrawlerUrlWrapper;
import colin.app.service.mlife.core.common.ReturnCommonResult;
import colin.app.service.mlife.core.common.SystemConstants;
import colin.app.service.mlife.core.dao.CrawlerURLDao;
import colin.app.service.mlife.core.pojo.CrawlerURL;
import colin.app.service.mlife.core.utils.MLifteDateUtils;
import colin.app.service.mlife.observer.CrawlerURLHandlerObserver;
import colin.app.service.mlife.service.CrawlerUrlService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;

/**
 * Created by joker on 16/8/21.
 */
@Service
public class CrawlerUrlServiceImpl extends Observable implements CrawlerUrlService{
    private final static Logger log= LoggerFactory.getLogger(CrawlerUrlServiceImpl.class);

    @Autowired
    private CrawlerURLDao crawlerURLDao;

    @Autowired
    private CrawlerURLHandlerObserver crawlerURLHandlerObserver;

    @PostConstruct
    public void initCrawlerObserver(){
        this.addObserver(crawlerURLHandlerObserver);
    }
    /**
     * 添加爬虫URL
     *
     * @param crawlerUrlWrapper
     */
    @Override
    public ReturnCommonResult addCrawlerURL(CrawlerUrlWrapper crawlerUrlWrapper) {
        ReturnCommonResult result=null;
        try {
            CrawlerURL crawlerURL=this.transferCrawler(crawlerUrlWrapper);
            crawlerURLDao.addCrawlerURL(crawlerURL);
            result=new ReturnCommonResult(true);
            this.setChanged();
            this.notifyObservers(crawlerURL);
        }catch (Exception e){
           log.error("添加爬取URL出错:{}",e);
            result=new ReturnCommonResult(false);
        }
        return result;
    }

    /**
     * 分页获取爬取链接
     *
     * @return
     */
    @Override
    public ReturnCommonResult listCrawlerURLsByPage(int pageIndex,int pageSize) {
        ReturnCommonResult commonResult=null;
        if(pageIndex<1){
            commonResult=new ReturnCommonResult(false);
        }else{
            if(pageSize<0){
                pageSize= SystemConstants.DEFAULT_PAGE_SIZE;
            }
            List<CrawlerURL> crawlerURLs=crawlerURLDao.findCrawlerURLByPage(pageIndex,pageSize);
            commonResult=new ReturnCommonResult(true,crawlerURLs);
        }
        return commonResult;
    }

    /**
     * 转换包装类
     * @param crawlerUrlWrapper
     * @return
     */
    private CrawlerURL transferCrawler(CrawlerUrlWrapper crawlerUrlWrapper){
        ObjectMapper mapper=new ObjectMapper();
        CrawlerURL crawlerURL=mapper.convertValue(crawlerUrlWrapper,CrawlerURL.class);
        String initDate= MLifteDateUtils.getCurrentDate();
        crawlerURL.setCreateDate(initDate);
        crawlerURL.setUpdateDate(initDate);
        return crawlerURL;
    }

}
