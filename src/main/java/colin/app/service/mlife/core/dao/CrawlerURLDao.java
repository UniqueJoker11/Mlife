package colin.app.service.mlife.core.dao;

import colin.app.service.mlife.core.pojo.CrawlerURL;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by joker on 16/8/20.
 */
@Repository
public class CrawlerURLDao {
    private final static String CRAWLER_URL_COLLECTION="crawler_url";
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 添加爬取URL
     * @param crawlerURL
     * @return
     */
    public void addCrawlerURL(CrawlerURL crawlerURL){
        mongoTemplate.insert(crawlerURL,CRAWLER_URL_COLLECTION);
    }

    /**
     * 根据URL删除链接
     * @param url
     */
    public boolean delCrawlerURL(String url){
        Query urlDelQuery= Query.query(new Criteria("url").is(url));
        WriteResult delResult=mongoTemplate.remove(urlDelQuery,CRAWLER_URL_COLLECTION);
        if (delResult.isUpdateOfExisting()){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 分页查询链接URL
     * @param pageIndex
     * @param dataSize
     * @return
     */
    public List<CrawlerURL> findCrawlerURLByPage(int pageIndex,int dataSize){
        Query urlPageQuery=Query.query(new Criteria()).skip((pageIndex-1)*dataSize).limit(dataSize);
        List<CrawlerURL> crawlerURLs=mongoTemplate.find(urlPageQuery,CrawlerURL.class,CRAWLER_URL_COLLECTION);
        return crawlerURLs;
    }

}
