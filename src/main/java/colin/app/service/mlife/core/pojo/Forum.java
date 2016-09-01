package colin.app.service.mlife.core.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 论坛版块
 * Created by Administrator on 2016/8/31.
 */
@Document
public class Forum {
    private String id;
    private String name;
    private boolean isTop;
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

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean isTop) {
        this.isTop = isTop;
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
}
