package colin.app.service.mlife.core.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 论坛版块
 * Created by Administrator on 2016/8/31.
 */
@Document
public class Forum {
    private String id;
    private String icon;
    private String name;
    private String digest;
    //板块分类 1:主版块 2:副版块
    private int category;
    //浏览数量
    private long browserNum;
    private String createTime;
    //话题个数
    private long themeNum;
    //帖子个数
    private long topicNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public long getBrowserNum() {
        return browserNum;
    }

    public void setBrowserNum(long browserNum) {
        this.browserNum = browserNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public long getThemeNum() {
        return themeNum;
    }

    public void setThemeNum(long themeNum) {
        this.themeNum = themeNum;
    }

    public long getTopicNum() {
        return topicNum;
    }

    public void setTopicNum(long topicNum) {
        this.topicNum = topicNum;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }
}
