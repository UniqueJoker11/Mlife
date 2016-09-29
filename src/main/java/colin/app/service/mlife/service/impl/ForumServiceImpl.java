package colin.app.service.mlife.service.impl;

import colin.app.service.mlife.controller.wrapper.ForumWrapper;
import colin.app.service.mlife.core.common.ReturnCommonResult;
import colin.app.service.mlife.core.common.SystemConstants;
import colin.app.service.mlife.core.convert.ForumConvert;
import colin.app.service.mlife.core.dao.ForumDao;
import colin.app.service.mlife.core.pojo.Forum;
import colin.app.service.mlife.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joker on 16/9/3.
 */
@Service
public class ForumServiceImpl implements ForumService{

    @Autowired
    private ForumConvert forumConvert;
    @Autowired
    private ForumDao forumDao;
    /**
     * 添加论坛版块
     *
     * @param forumWrapper
     * @return
     */
    @Override
    public ReturnCommonResult addForum(ForumWrapper forumWrapper) {
        Forum forum=forumConvert.initForum(forumWrapper);
        if(null==forum){
            return new ReturnCommonResult(false,"添加的论坛主题不能为空");
        }
        forumDao.addForum(forum);
        return new ReturnCommonResult(true);
    }

    /**
     * 查询所有的论坛版块
     *
     * @return
     */
    @Override
    public ReturnCommonResult findForumByPage(int pageIndex,int dataSize) {
        long forumNum=forumDao.countAllForum();
        if (forumNum==0){
            return new ReturnCommonResult(false);
        }else{
            Map<String,Object> resultMap=new HashMap<String,Object>();
            long pageTotal=forumNum% SystemConstants.DEFAULT_PAGE_SIZE==0?forumNum/SystemConstants.DEFAULT_PAGE_SIZE:forumNum/SystemConstants.DEFAULT_PAGE_SIZE+1;
            resultMap.put("count",pageTotal);
            resultMap.put("data",forumDao.findForumByPage(pageIndex,dataSize));
            return  new ReturnCommonResult(true,resultMap);
        }
    }

    /**
     * 查询所有的论坛版块
     *
     * @return
     */
    @Override
    public ReturnCommonResult findAllForum() {
        List<Forum> forumList=forumDao.findAllForum();
        if (ObjectUtils.isEmpty(forumList)){
            return new ReturnCommonResult(false);
        }
        return new ReturnCommonResult(true,forumList);
    }

}
