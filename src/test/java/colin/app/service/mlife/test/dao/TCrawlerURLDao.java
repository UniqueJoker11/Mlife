package colin.app.service.mlife.test.dao;

import colin.app.service.mlife.core.dao.CrawlerURLDao;
import colin.app.service.mlife.core.pojo.CrawlerURL;
import colin.app.service.mlife.test.CommonTestConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */
public class TCrawlerURLDao extends CommonTestConfig {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CrawlerURLDao crawlerURLDao;

    @Test
    public void getCrawlerInfo(){
        List<CrawlerURL> crawlerURLs=crawlerURLDao.findCrawlerURLByPage(1,1);
        ObjectMapper mapper=new ObjectMapper();
        try {
            logger.info(mapper.writeValueAsString(crawlerURLs));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateCrawlerURL(){
      List<String> ids=new ArrayList<String>();
        ids.add("57b9ba6cc4b598bbee9f64a5");
        crawlerURLDao.updateCrawlerURL(ids);
    }


}
