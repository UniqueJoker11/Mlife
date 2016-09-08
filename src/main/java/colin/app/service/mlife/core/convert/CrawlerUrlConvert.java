package colin.app.service.mlife.core.convert;

import colin.app.service.mlife.controller.wrapper.CrawlerUrlWrapper;
import colin.app.service.mlife.core.pojo.CrawlerURL;
import colin.app.service.mlife.core.utils.MLifteDateUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by joker on 16/9/4.
 */
@Component
public class CrawlerUrlConvert implements Converter<CrawlerUrlWrapper,CrawlerURL>{
    @Override
    public CrawlerURL convert(CrawlerUrlWrapper crawlerUrlWrapper) {
        if(null==crawlerUrlWrapper){
            return null;
        }
        ObjectMapper mapper=new ObjectMapper();
        CrawlerURL crawlerURL=mapper.convertValue(crawlerUrlWrapper,CrawlerURL.class);
        return crawlerURL;
    }
    public CrawlerURL initCrawlerURL(CrawlerUrlWrapper crawlerUrlWrapper){
        CrawlerURL crawlerURL=this.convert(crawlerUrlWrapper);
        if(null==crawlerURL){
            return null;
        }
        String initDate= MLifteDateUtils.getCurrentDate();
        crawlerURL.setCreateDate(initDate);
        crawlerURL.setUpdateDate(initDate);
        return crawlerURL;

    }
}
