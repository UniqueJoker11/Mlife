package colin.app.service.mlife.core.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/8/26.
 */
@Repository
public class CrawlerParserDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 添加爬虫解析器
     */
    public void addCrawlerParser(){

    }

    public void updateCrawlerParser(){

    }
}
