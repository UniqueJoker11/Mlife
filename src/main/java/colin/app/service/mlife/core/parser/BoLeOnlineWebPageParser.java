package colin.app.service.mlife.core.parser;

import colin.app.service.mlife.core.pojo.CrawlerURL;
import colin.app.service.mlife.core.utils.MLifteDateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 */
public class BoLeOnlineWebPageParser extends CrawlerBaseParser<CrawlerURL>{

    private final static Logger log = LoggerFactory.getLogger(BoLeOnlineWebPageParser.class);
    /**
     * 处理解析页面
     */
    @Override
    public List<CrawlerURL> urlPageHandler() {
        try {
            //获取加载
            Document pageDoc = Jsoup.connect(super.getUrl()).get();
            //获取Web所有文章页数连接
            Element bodyEle = pageDoc.body();
            return parsePageIndexUrl(bodyEle);
        } catch (IOException e) {
            log.error("爬取文章出现问题,{}", e);
            return null;
        }
    }

    public List<CrawlerURL> parsePageIndexUrl(Element bodyEle) {
        Elements navPageEle = bodyEle.getElementsByClass("navigation");
        if (navPageEle.size() > 0) {
            Element urlNavEle = navPageEle.first();
            //获取a连接标签
            Elements urlLinkEles = urlNavEle.getElementsByTag("a");
            //最大的页数数字
            Element aLinkEle = urlLinkEles.get(urlLinkEles.size() - 2);
            int maxPageIndex = Integer.valueOf(aLinkEle.text());
            String initUrlLink=super.getUrl().endsWith("/")?super.getUrl():super.getUrl()+"/";
            StringBuilder crawlerUrl = new StringBuilder(initUrlLink+"page/");
            List<CrawlerURL> crawlerURLs = new ArrayList<CrawlerURL>();
            for (int i = 1; i <= maxPageIndex; i++) {
                if(i==1){
                    CrawlerURL crawlerURLObj = combineCrawlerURL(this.getUrl(), this.getUrl());
                    crawlerURLs.add(crawlerURLObj);
                }else{
                    crawlerUrl.append(i).append("/");
                    CrawlerURL crawlerURLObj = combineCrawlerURL(this.getUrl(), crawlerUrl.toString());
                    crawlerURLs.add(crawlerURLObj);
                }
                crawlerUrl=new StringBuilder(initUrlLink+"page/");
            }
            return crawlerURLs;
        } else {
            log.warn("查询不到响应的分页连接");
            return null;
        }
    }

    private CrawlerURL combineCrawlerURL(String parentUrl, String url) {
        CrawlerURL crawlerURL = new CrawlerURL();
        crawlerURL.setUrl(url);
        crawlerURL.setCrawlerStatus(0);
        crawlerURL.setCreateDate(MLifteDateUtils.getCurrentDate());
        crawlerURL.setIsRootUrl(0);
        crawlerURL.setParentUrl(parentUrl);
        crawlerURL.setUpdateDate(MLifteDateUtils.getCurrentDate());
        crawlerURL.setCrawlerParser("colin.app.service.mlife.core.parser.BoLeOnlineAticlePageParser");
        crawlerURL.setUrlDecription("伯乐在线文章列表页");
        return crawlerURL;
    }
}
