package colin.app.service.mlife.core.dao;

import colin.app.service.mlife.core.pojo.CrawlerAticle;
import colin.app.service.mlife.core.pojo.CrawlerAticleDigest;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 */
@Repository
public class CrawlerAticleDao {
    private final static String CRAWLER_ATICLE_COLLECTIONS = "crawler_aticle";
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 批量添加文章
     *
     * @param crawlerAticles
     */
    public void addCrawlerAticles(List<CrawlerAticle> crawlerAticles) {
        mongoTemplate.insert(crawlerAticles, CRAWLER_ATICLE_COLLECTIONS);
    }

    /**
     * 分页查询爬取的文章
     *
     * @param pageIndex
     * @param dataSize
     * @return
     */
    public List<CrawlerAticleDigest> findAllCrawlerAticleDigestByPage(int pageIndex, int dataSize) {
        DBObject fieldObject = new BasicDBObject();
        fieldObject.put("aticleContent", 0);
        fieldObject.put("_class", 0);
        Query crawlerAticleDigestQuery = new BasicQuery(new BasicDBObject(), fieldObject);
        crawlerAticleDigestQuery.addCriteria(new Criteria()).skip((pageIndex - 1) * dataSize).limit(dataSize);
        return mongoTemplate.find(crawlerAticleDigestQuery, CrawlerAticleDigest.class, CRAWLER_ATICLE_COLLECTIONS);
    }

    /**
     * 查询文章详情
     *
     * @param atcileId
     * @return
     */
    public CrawlerAticle findCrawlerAticleDetail(String atcileId) {
        Query crawlerAticleDetailQuery = Query.query(Criteria.where("_id").is(atcileId));
        List<CrawlerAticle> crawlerAticles = mongoTemplate.find(crawlerAticleDetailQuery, CrawlerAticle.class, CRAWLER_ATICLE_COLLECTIONS);
        if (null == crawlerAticles || crawlerAticles.isEmpty()) {
            return null;
        } else {
            return crawlerAticles.get(0);
        }
    }

    /**
     * 统计所有的文章数量
     *
     * @return
     */
    public long countAllCrawlerAticles() {
        return mongoTemplate.count(null, CRAWLER_ATICLE_COLLECTIONS);
    }
}
