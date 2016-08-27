package colin.app.service.mlife.service;

import colin.app.service.mlife.core.common.ReturnCommonResult;

/**
 * Created by Administrator on 2016/8/27.
 */
public interface CrawlerAticleService {

    /**
     * 分页获取文章列表内容
     * @param pageIndex
     * @param dataSize
     * @return
     */
    public ReturnCommonResult findCrawlerAticleDigestByPage(int pageIndex,int dataSize);

    /**
     * 查找文章详情
     * @param aticleId
     * @return
     */
    public ReturnCommonResult findCrawlerAticleDetail(String aticleId);


}
