package colin.app.service.mlife.schedule;

import colin.app.service.mlife.core.dao.CrawlerAticleDao;
import colin.app.service.mlife.core.dao.CrawlerURLDao;
import colin.app.service.mlife.core.parser.CrawlerBaseParser;
import colin.app.service.mlife.core.pojo.CrawlerAticle;
import colin.app.service.mlife.core.pojo.CrawlerURL;
import colin.app.service.mlife.core.utils.MLifteDateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/8/26.
 */
@Component
public class PageCrawlerSchedule {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CrawlerAticleDao crawlerAticleDao;
    @Autowired
    private CrawlerURLDao crawlerURLDao;

    /**
     * 定时爬取文章内容
     */
    @Scheduled(cron = "0 0/10 * * * ?") //每半小时分钟执行一次
    public void setCrawlerAticleSchedule() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        //获取所有未爬取的链接
       List<CrawlerURL> crawlerURLList = crawlerURLDao.findUnCrawlerURL();
        if (null!=crawlerURLList&&crawlerURLList.size()>0){
            //通过其页面解析器来保存其对应的文章内容
            ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
            List<CrawlerAticle> aticleLists = new ArrayList<CrawlerAticle>();
            List<String> ids=new ArrayList<String>();
            for(CrawlerURL crawlerURL:crawlerURLList){
                Class crawlerClass=classLoader.loadClass(crawlerURL.getCrawlerParser());
                CrawlerBaseParser<CrawlerAticle> crawlerBaseParser=(CrawlerBaseParser)crawlerClass.newInstance();
                crawlerBaseParser.setUrl(crawlerURL.getUrl());
                aticleLists.addAll(crawlerBaseParser.urlPageHandler());
                ids.add(crawlerURL.getId());
                crawlerAticleDao.addCrawlerAticles(aticleLists);
                //更新用戶的鏈接狀態
                crawlerURLDao.updateCrawlerURL(ids);
                ids.clear();
                aticleLists.clear();
                log.info("爬取链接"+crawlerURL.getUrl()+"完毕！");
            }

        }
    }

}
