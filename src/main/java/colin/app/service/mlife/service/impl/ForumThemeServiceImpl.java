package colin.app.service.mlife.service.impl;

import colin.app.service.mlife.controller.wrapper.ForumThemeWrapper;
import colin.app.service.mlife.core.common.ReturnCommonResult;
import colin.app.service.mlife.core.convert.ForumThemeConvert;
import colin.app.service.mlife.core.dao.ForumThemeDao;
import colin.app.service.mlife.core.pojo.ForumTheme;
import colin.app.service.mlife.core.utils.MLifteDateUtils;
import colin.app.service.mlife.service.ForumThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * Created by joker on 16/9/4.
 */
@Service
public class ForumThemeServiceImpl implements ForumThemeService{

    @Autowired
    private ForumThemeDao forumThemeDao;

    @Autowired
    private ForumThemeConvert forumThemeConvert;
    /**
     * 添加论坛话题
     *
     * @param forumThemeWrapper@return
     */
    @Override
    public ReturnCommonResult addForumTheme(ForumThemeWrapper forumThemeWrapper) {
        ForumTheme forumTheme=transferToForumTheme(forumThemeWrapper);
        if (ObjectUtils.isEmpty(forumTheme)){
            return new ReturnCommonResult(false,"参数不正确");
        }
        forumThemeDao.addForum(forumTheme);
        return new ReturnCommonResult(true);
    }

    /**
     * 查询所有的论坛话题
     *
     * @param pageIndex
     * @param dataSize
     * @return
     */
    @Override
    public ReturnCommonResult findForumThemeByPage(int pageIndex, int dataSize) {
        return null;
    }

    /**
     * 转换话题类
     * @param forumThemeWrapper
     * @return
     */
    private ForumTheme transferToForumTheme(ForumThemeWrapper forumThemeWrapper){
        if(ObjectUtils.isEmpty(forumThemeWrapper)){
           return null;
        }
        ForumTheme forumTheme=new ForumTheme();
        forumTheme.setForumId(forumThemeWrapper.getForumId());
        forumTheme.setThemeAuthor(forumThemeWrapper.getThemeAuthor());
        forumTheme.setThemeDescribe(forumThemeWrapper.getThemeDescribe());
        forumTheme.setThemeTitle(forumThemeWrapper.getThemeTitle());
        forumTheme.setThemeCreateTime(MLifteDateUtils.getCurrentDate());
        return forumTheme;
    }
}
