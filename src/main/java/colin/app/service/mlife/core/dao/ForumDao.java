package colin.app.service.mlife.core.dao;

import colin.app.service.mlife.core.pojo.Forum;
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
public class ForumDao {

    private static final String FORUM_COLLECTION="forum";
    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 添加论坛主题
     * @param forum
     */
    public void addForum(Forum forum){
        mongoTemplate.insert(forum,FORUM_COLLECTION);
    }

    /**
     * 更新主题内容
     * @param id
     * @param updateContent
     */
    public void updateForum(String id,Map<String,Object> updateContent){
        Query forumQuery=Query.query(Criteria.where("_id").is(id));
        Update forumUpdate=new Update();
        forumUpdate.getUpdateObject().putAll(updateContent);
        mongoTemplate.updateFirst(forumQuery,forumUpdate,FORUM_COLLECTION);
    }

    /**
     * 查询所有的主题
     * @return
     */
    public List<Forum> findTopForums(){
        Query topForums=Query.query(Criteria.where("isTop").is(true));
        List<Forum> forums=mongoTemplate.find(topForums,Forum.class,FORUM_COLLECTION);
        return forums;
    }

    /**
     * 查询所有的普通主题
     * @return
     */
    public List<Forum> findNormalForums(){
        Query topForums=Query.query(Criteria.where("isTop").is(false));
        List<Forum> forums=mongoTemplate.find(topForums,Forum.class,FORUM_COLLECTION);
        return forums;
    }
}
