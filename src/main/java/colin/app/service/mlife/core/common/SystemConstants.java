package colin.app.service.mlife.core.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by joker on 16/8/21.
 */
public class SystemConstants {

    //默认分页大小
    public final static int DEFAULT_PAGE_SIZE=15;
    //默认线程池
    public final static ExecutorService DEFAULT_THREAD_POOL= Executors.newFixedThreadPool(10);
    //微信内容配置
    public final static String WCHAT_TOKEN="13RTYHJ456FGHJ67FGHMrtVBNM452DFGBN";
    public final static String SHA1_ENCODING_KEY="eH6MW1IEgAtESQMwqZitIW6ZOcjSXXsAai7CyNORWek";
}
