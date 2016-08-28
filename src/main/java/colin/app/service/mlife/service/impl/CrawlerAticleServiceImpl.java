package colin.app.service.mlife.service.impl;

import colin.app.service.mlife.core.common.ReturnCommonResult;
import colin.app.service.mlife.core.common.SystemConstants;
import colin.app.service.mlife.core.dao.CrawlerAticleDao;
import colin.app.service.mlife.core.pojo.CrawlerAticle;
import colin.app.service.mlife.core.pojo.CrawlerAticleDigest;
import colin.app.service.mlife.service.CrawlerAticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2016/8/27.
 */
@Service
public class CrawlerAticleServiceImpl implements CrawlerAticleService{

    @Autowired
    private CrawlerAticleDao crawlerAticleDao;
    /**
     * 分页获取文章列表内容
     *
     * @param pageIndex
     * @param dataSize
     * @return
     */
    @Override
    public ReturnCommonResult findCrawlerAticleDigestByPage(int pageIndex, int dataSize) {
        if (dataSize<=0){
            dataSize= SystemConstants.DEFAULT_PAGE_SIZE;
        }
        List<CrawlerAticleDigest> crawlerAticleDigestsList=crawlerAticleDao.findAllCrawlerAticleDigestByPage(pageIndex,dataSize);
        ReturnCommonResult result=null;
        if (null==crawlerAticleDigestsList||crawlerAticleDigestsList.isEmpty()){
            result=new ReturnCommonResult(false);
        }else{
            long aticleCount=crawlerAticleDao.countAllCrawlerAticles();
            Map<String,Object> aticleData=new HashMap<String,Object>();
            //总页数
            long total=aticleCount%dataSize>0?aticleCount/dataSize+1:aticleCount/dataSize;
            aticleData.put("total",total);
            //当前页
            aticleData.put("current",pageIndex);
            //是否有下一页
            aticleData.put("hasNext",pageIndex==total?false:true);
            //是否有上一页
            aticleData.put("hasPrev",pageIndex==1?false:true);
            //当前页数据
            aticleData.put("data",crawlerAticleDigestsList);
            //返回结果
            result=new ReturnCommonResult(true,aticleData);
        }
        return result;
    }

    /**
     * 查找文章详情
     *
     * @param aticleId
     * @return
     */
    @Override
    public ReturnCommonResult findCrawlerAticleDetail(String aticleId) {
        ReturnCommonResult result=null;
        if(StringUtils.isEmpty(aticleId)){
            result=new ReturnCommonResult(false);
        }else{
            CrawlerAticle crawlerAticle=crawlerAticleDao.findCrawlerAticleDetail(aticleId);
            if (null==crawlerAticle){
                result=new ReturnCommonResult(false);
            }else {
                result=new ReturnCommonResult(true,crawlerAticle);
            }
        }
        return result;
    }
}
