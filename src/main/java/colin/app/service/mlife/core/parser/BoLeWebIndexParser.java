package colin.app.service.mlife.core.parser;

import colin.app.service.mlife.core.pojo.CrawlerAticle;
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
public class BoLeWebIndexParser extends CrawlerBaseParser {
    private final static Logger log = LoggerFactory.getLogger(BoLeWebIndexParser.class);
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 处理解析页面
     */
    @Override
    public  List<CrawlerAticle> urlPageHandler() {
        try {
            //获取加载
            Document pageDoc = Jsoup.connect(url).get();
            Element bodyEle = pageDoc.body();
            List<CrawlerAticle> crawlerAticles=crawlerAticles(bodyEle);
            return crawlerAticles;
        } catch (IOException e) {
            log.info("爬取文章出现问题,{}",e);
            return null;
        }
    }

    public List<CrawlerAticle> crawlerAticles(Element bodyEle) throws IOException {
        //获取菜单及诶单
        Element navEle = bodyEle.getElementById("main-nav-menu");
        //将其作为根节点按顺序去爬取
        Elements navChildrenEles = navEle.children();
        List<CrawlerAticle> crawlerAticleList = new ArrayList<CrawlerAticle>();
        for (Element navChildrenEle : navChildrenEles) {
            crawlerAticleList.addAll(combineCrawlerAticle(navChildrenEle));
        }
        return crawlerAticleList;
    }

    public  List<CrawlerAticle> combineCrawlerAticleList(Element navChildrenEle) throws IOException {
        //获取根节点
        String navHref = navChildrenEle.child(0).attr("href");
        Document navDoc = Jsoup.connect(navHref).get();
        Element aticleEles = navDoc.getElementById("archive");
        return combineCrawlerAticle(aticleEles);
    }

    public  List<CrawlerAticle> combineCrawlerAticle(Element aticleEles) throws IOException {
        List<CrawlerAticle> crawlerAticleList=new ArrayList<CrawlerAticle>(20);
        Elements aticleList = aticleEles.children();
        for (Element aticleEle : aticleEles.getAllElements()) {
            crawlerAticleList.add(parseCrawlerAticle(aticleEle));
        }
        return crawlerAticleList;
    }

    public CrawlerAticle parseCrawlerAticle(Element aticleEle) throws IOException {
        Element aticleDigestEle = aticleEle.getElementsByClass("post-thumb").get(0);
        CrawlerAticle crawlerAticle = new CrawlerAticle();
        crawlerAticle.setReprintURL(aticleDigestEle.attr("href"));
        crawlerAticle.setTitle(aticleDigestEle.attr("title"));
        crawlerAticle.setDigestSnapshot(aticleDigestEle.child(0).attr("src"));
        Document aticleContent = Jsoup.connect(aticleDigestEle.attr("href")).get();
        crawlerAticle.setAticleContent(aticleContent.getElementsByClass("entry").get(0).html());
        Element aticleDetailEle = aticleEle.getElementsByClass("post-meta").get(0);
        if (null != aticleDetailEle.child(0).getElementsByTag("a")) {
            Element categoryEle = aticleDetailEle.child(0).getElementsByTag("a").get(1);
            crawlerAticle.setAticleCategory(categoryEle.text());
        }
        crawlerAticle.setDigest(aticleDetailEle.getElementsByClass("excerpt").get(0).text());
        return crawlerAticle;
    }
}
