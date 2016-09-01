package colin.app.service.mlife.core.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/8/31.
 */
@Repository
public class ForumThemeDao {
    @Autowired
    private MongoTemplate mongoTemplate;
}
