package colin.app.service.mlife.core.dao;

import colin.app.service.mlife.core.common.SystemConstants;
import colin.app.service.mlife.core.pojo.CrawlerAticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 */
@Repository
public class CrawlerAticleDao {
    private final static String CRAWLER_ATICLE_COLLECTIONS="crawler_aticle";
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 批量添加文章
     * @param crawlerAticles
     */
    public void addCrawlerAticles(List<CrawlerAticle> crawlerAticles){
        mongoTemplate.insert(crawlerAticles,CRAWLER_ATICLE_COLLECTIONS);
    }
}
