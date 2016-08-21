package colin.app.service.mlife.core.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by joker on 16/8/20.
 */
@Document
public class CrawlerURL implements Serializable{
    private String url;
    //链接描述
    private String urlDecription;
    //是否是根节点
    private boolean isRootUrl;
    //父级节点
    private String parentUrl;
    //页面解析器
    private String crawlerParser;
    //爬取状态
    private int crawlerStatus;
    //创建日期
    private String createDate;
    //更新日期
    private String updateDate;

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getParentUrl() {
        return parentUrl;
    }

    public void setParentUrl(String parentUrl) {
        this.parentUrl = parentUrl;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

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

    public boolean isRootUrl() {
        return isRootUrl;
    }

    public void setRootUrl(boolean rootUrl) {
        isRootUrl = rootUrl;
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
