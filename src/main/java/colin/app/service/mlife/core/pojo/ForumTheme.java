package colin.app.service.mlife.core.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 论坛话题
 * Created by Administrator on 2016/8/31.
 */
@Document
public class ForumTheme {
    private String id;
    private String forumId;
    private String themeAuthor;
    private String themeTitle;
    private String themeDescribe;
    private String themeCreateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getForumId() {
        return forumId;
    }

    public void setForumId(String forumId) {
        this.forumId = forumId;
    }

    public String getThemeAuthor() {
        return themeAuthor;
    }

    public void setThemeAuthor(String themeAuthor) {
        this.themeAuthor = themeAuthor;
    }

    public String getThemeTitle() {
        return themeTitle;
    }

    public void setThemeTitle(String themeTitle) {
        this.themeTitle = themeTitle;
    }

    public String getThemeDescribe() {
        return themeDescribe;
    }

    public void setThemeDescribe(String themeDescribe) {
        this.themeDescribe = themeDescribe;
    }

    public String getThemeCreateTime() {
        return themeCreateTime;
    }

    public void setThemeCreateTime(String themeCreateTime) {
        this.themeCreateTime = themeCreateTime;
    }
}
