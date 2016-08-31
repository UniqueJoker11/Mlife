package colin.app.service.mlife.core.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 论坛帖子
 * Created by Administrator on 2016/8/31.
 */
@Document
public class ForumTopic {

    private String id;
    private String forumId;
    private String topicAuthor;
    private String topicTitle;
    private String topicContent;
    private String topicCreateTime;

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

    public String getTopicAuthor() {
        return topicAuthor;
    }

    public void setTopicAuthor(String topicAuthor) {
        this.topicAuthor = topicAuthor;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getTopicCreateTime() {
        return topicCreateTime;
    }

    public void setTopicCreateTime(String topicCreateTime) {
        this.topicCreateTime = topicCreateTime;
    }
}
