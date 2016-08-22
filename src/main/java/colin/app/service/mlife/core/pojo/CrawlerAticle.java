package colin.app.service.mlife.core.pojo;

/**
 * Created by Administrator on 2016/8/22.
 */
public class CrawlerAticle {
    //博文标题
    private String title;
    //摘要
    private String digest;
    //摘要缩略图
    private String digestSnapshot;
    //标签
    private String tips;
    //文章分类
    private String aticleCategory;
    //全文
    private String aticleContent;
    //抓取时间
    private String updateTime;
    //转载链接
    private String reprintURL;
    //文章作者
    private String aticleAuthor;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getDigestSnapshot() {
        return digestSnapshot;
    }

    public void setDigestSnapshot(String digestSnapshot) {
        this.digestSnapshot = digestSnapshot;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getAticleCategory() {
        return aticleCategory;
    }

    public void setAticleCategory(String aticleCategory) {
        this.aticleCategory = aticleCategory;
    }

    public String getAticleContent() {
        return aticleContent;
    }

    public void setAticleContent(String aticleContent) {
        this.aticleContent = aticleContent;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getReprintURL() {
        return reprintURL;
    }

    public void setReprintURL(String reprintURL) {
        this.reprintURL = reprintURL;
    }

    public String getAticleAuthor() {
        return aticleAuthor;
    }

    public void setAticleAuthor(String aticleAuthor) {
        this.aticleAuthor = aticleAuthor;
    }
}
