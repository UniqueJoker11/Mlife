package colin.app.service.mlife.controller.wrapper;

/**
 * Created by joker on 16/8/20.
 */
public class CrawlerUrlWrapper {
    private String url;
    //链接描述
    private String urlDecription;
    //是否是根节点
    private int isRootUrl;
    //页面解析器
    private String crawlerParser;
    //爬取状态
    private int crawlerStatus;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlDecription() {
        return urlDecription;
    }

    public void setUrlDecription(String urlDecription) {
        this.urlDecription = urlDecription;
    }

    public int getIsRootUrl() {
        return isRootUrl;
    }

    public void setIsRootUrl(int isRootUrl) {
        this.isRootUrl = isRootUrl;
    }

    public String getCrawlerParser() {
        return crawlerParser;
    }

    public void setCrawlerParser(String crawlerParser) {
        this.crawlerParser = crawlerParser;
    }

    public int getCrawlerStatus() {
        return crawlerStatus;
    }

    public void setCrawlerStatus(int crawlerStatus) {
        this.crawlerStatus = crawlerStatus;
    }
}
