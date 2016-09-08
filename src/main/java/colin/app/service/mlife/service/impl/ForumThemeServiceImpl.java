package colin.app.service.mlife.service.impl;

import colin.app.service.mlife.controller.wrapper.ForumThemeWrapper;
import colin.app.service.mlife.core.common.ReturnCommonResult;
import colin.app.service.mlife.core.convert.ForumThemeConvert;
import colin.app.service.mlife.core.dao.ForumThemeDao;
import colin.app.service.mlife.service.ForumThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 添加论坛版块
     *
     * @param forumThemeWrapper@return
     */
    @Override
    public ReturnCommonResult addForumTheme(ForumThemeWrapper forumThemeWrapper) {
        return null;
    }

    /**
     * 查询所有的论坛版块
     *
     * @param pageIndex
     * @param dataSize
     * @return
     */
    @Override
    public ReturnCommonResult findForumThemeByPage(int pageIndex, int dataSize) {
        return null;
    }
}
