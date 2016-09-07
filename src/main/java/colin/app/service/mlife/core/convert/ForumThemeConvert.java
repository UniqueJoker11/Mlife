package colin.app.service.mlife.core.convert;

import org.springframework.stereotype.Component;

/**
 * Created by joker on 16/9/4.
 */
@Component
public class ForumThemeConvert {
    private String forumId;
    private String themeAuthor;
    private String themeTitle;
    private String themeDescribe;

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
}
