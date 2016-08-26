package colin.app.service.mlife.core.parser;

import colin.app.service.mlife.core.pojo.CrawlerAticle;
import colin.app.service.mlife.core.utils.MLifteDateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joker on 16/8/21.
 */
public class BoLeOnlineAticlePageParser extends CrawlerBaseParser<CrawlerAticle>{

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private static final int DEFAULT_TIMEOUT=5000;
    /**
     * 处理解析的具体内容
     */
    @Override
    public List<CrawlerAticle> urlPageHandler() throws IOException {
        return combineCrawlerAticles();
    }
    /**
     * 处理文章列表
     * @return
     * @throws java.io.IOException
     */
    private List<CrawlerAticle> combineCrawlerAticles() {
        Element aticleEle = null;
        try {
            aticleEle = Jsoup.connect(super.getUrl()).timeout(DEFAULT_TIMEOUT).get().body();
            Element aticleContainerEle = aticleEle.getElementById("archive");
            Elements aticleListEles = aticleContainerEle.getElementsByClass("floated-thumb");
            List<CrawlerAticle> crawlerAticleList=new ArrayList<CrawlerAticle>();
            int aticleListSize = aticleListEles.size();
            for (int i = 0; i < aticleListSize; i++) {
                Element aticleDetailEle = aticleListEles.get(i);
                crawlerAticleList.add(combineCrawlerAticle(aticleDetailEle));
            }
            return crawlerAticleList;
        } catch (IOException e) {
            log.error("当前连接访问超时,{},{}",super.getUrl(),e);
            return null;
        }

    }

    /**
     * 组装文章对象
     * @param aticleDetailEle
     * @return
     * @throws IOException
     */
    private CrawlerAticle combineCrawlerAticle(Element aticleDetailEle) throws IOException {
        CrawlerAticle crawlerAticle = new CrawlerAticle();
        Elements digestSnapshotEle=aticleDetailEle.getElementsByClass("post-thumb");
        if(null!=digestSnapshotEle&&digestSnapshotEle.size()>0){
            String digestSnapshot = aticleDetailEle.getElementsByClass("post-thumb").first().getElementsByTag("img").attr("src");
            crawlerAticle.setDigestSnapshot(digestSnapshot);
        }
        Element digestAticle = aticleDetailEle.getElementsByClass("post-meta").first();
        crawlerAticle.setTitle(digestAticle.getElementsByClass("archive-title").first().attr("title"));
        crawlerAticle.setDigest(digestAticle.getElementsByClass("excerpt").first().text());
        Elements aticleCategory=digestAticle.getElementsByAttributeValue("rel", "category tag");
        crawlerAticle.setAticleCategory(null == aticleCategory || aticleCategory.size()==0 ? "" : aticleCategory.first().text());
        String aticleUrl = digestAticle.getElementsByClass("archive-title").first().attr("href");
        crawlerAticle.setReprintURL(aticleUrl);
        crawlerAticle.setUpdateTime(MLifteDateUtils.getCurrentDate());
        Element aticleBodyEle = Jsoup.connect(aticleUrl).timeout(DEFAULT_TIMEOUT).get().body();
        crawlerAticle.setAticleContent(aticleBodyEle.getElementsByClass("entry").first().text());
        Elements aticleTipsEles = aticleBodyEle.getElementsByClass("entry-meta").first().getElementsByTag("a");
        int aticleTipsElesSize = aticleTipsEles.size();
        StringBuilder tipsBuilder = new StringBuilder("");
        for (int i = 0; i < aticleTipsElesSize; i++) {
            Element aticleTipEle = aticleTipsEles.get(i);
            if (!aticleTipEle.hasAttr("rel")) {
                tipsBuilder.append(aticleTipEle.text()).append(",");
            }
        }
        crawlerAticle.setTips(tipsBuilder.toString());
        return crawlerAticle;
    }
}
