package colin.app.service.mlife.core.parser;


import colin.app.service.mlife.core.pojo.CrawlerAticle;
import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 */
public abstract class CrawlerBaseParser {

    /**
     * 处理解析的具体内容
     */
    public abstract List<CrawlerAticle> urlPageHandler();
}
