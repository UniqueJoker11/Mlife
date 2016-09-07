package colin.app.service.mlife.core.dao;

import colin.app.service.mlife.core.pojo.ForumTheme;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/31.
 */
@Repository
public class ForumThemeDao {
    private static final String FORUM_THEME_COLLECTION="forum_theme";
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 添加论坛话题
     * @param forum
     */
    public void addForum(ForumTheme forum){
        mongoTemplate.insert(forum,FORUM_THEME_COLLECTION);
    }

    /**
     * 更新话题内容
     * @param id
     * @param updateContent
     */
    public void updateForum(String id,Map<String,Object> updateContent){
        Query forumQuery=Query.query(Criteria.where("_id").is(id));
        Update forumUpdate=new Update();
        forumUpdate.getUpdateObject().putAll(updateContent);
        mongoTemplate.updateFirst(forumQuery,forumUpdate,FORUM_THEME_COLLECTION);
    }


    /**
     * 统计所有论坛版块数量
     * @return
     */
    public long countAllForumTheme(String forumId){
        if(StringUtils.isEmpty(forumId)){
            return mongoTemplate.count(null,FORUM_THEME_COLLECTION);
        }
        Query forumTheme= Query.query(Criteria.where("forumId").is(forumId));
        return mongoTemplate.count(forumTheme,FORUM_THEME_COLLECTION);
    }


    /**
     * 分页查询论坛话题
     * @param pageIndex
     * @param dataSize
     * @return
     */
    public List<ForumTheme> findForumThemeByPage(int pageIndex,int dataSize){
        Query forumThemeQuery=Query.query(new Criteria()).skip((pageIndex-1)*dataSize).limit(dataSize);
        List<ForumTheme> forumThemes=mongoTemplate.find(forumThemeQuery,ForumTheme.class,FORUM_THEME_COLLECTION);
        return forumThemes;
    }
}
