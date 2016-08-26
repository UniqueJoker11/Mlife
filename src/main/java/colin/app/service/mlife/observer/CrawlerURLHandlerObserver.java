package colin.app.service.mlife.observer;

import colin.app.service.mlife.core.common.SystemConstants;
import colin.app.service.mlife.core.dao.CrawlerURLDao;
import colin.app.service.mlife.core.parser.CrawlerBaseParser;
import colin.app.service.mlife.core.pojo.CrawlerURL;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * 爬虫连接处理观察者
 * Created by Administrator on 2016/8/22.
 */
@Component
public class CrawlerURLHandlerObserver implements Observer {
    private final static Logger log = LoggerFactory.getLogger(CrawlerURLHandlerObserver.class);

    @Autowired
    private CrawlerURLDao crawlerURLDao;

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        CrawlerURL crawlerURL = (CrawlerURL) arg;
        //异步处理网页链接内容
        //爬取网页链接，根据响应的解析器来处理网页内容
        ObjectMapper wrappedObject = new ObjectMapper();
        CrawlerUrlParserTask crawlerUrlParserTask = new CrawlerUrlParserTask(crawlerURL);
        SystemConstants.DEFAULT_THREAD_POOL.submit(crawlerUrlParserTask);
    }

    /**
     * 异步执行爬取任务
     */
    protected class CrawlerUrlParserTask implements Runnable {
        private CrawlerURL crawlerURL;

        public CrawlerUrlParserTask(CrawlerURL crawlerURL) {
            this.crawlerURL = crawlerURL;
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p/>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            try {
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                String parserClass = crawlerURL.getCrawlerParser();
                Class loadClass = classLoader.loadClass(parserClass);
                CrawlerBaseParser<CrawlerURL> parser = (CrawlerBaseParser) loadClass.newInstance();
                parser.setUrl(crawlerURL.getUrl());
                //添加要爬取的链接
                crawlerURLDao.addCrawlerURLs(parser.urlPageHandler());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
