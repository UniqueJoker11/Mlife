package colin.app.service.mlife.core.parser;


import colin.app.service.mlife.core.pojo.CrawlerURL;

import java.io.IOException;
import java.util.List;
import java.util.Observable;

/**
 * Created by Administrator on 2016/8/22.
 */
public abstract class CrawlerBaseParser<T>{
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * 处理解析的具体内容
     */
    public abstract List<T> urlPageHandler() throws IOException;
}
