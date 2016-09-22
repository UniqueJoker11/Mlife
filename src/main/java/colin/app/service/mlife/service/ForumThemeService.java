package colin.app.service.mlife.service;

import colin.app.service.mlife.controller.wrapper.ForumThemeWrapper;
import colin.app.service.mlife.core.common.ReturnCommonResult;

/**
 * Created by joker on 16/9/4.
 */
public interface ForumThemeService {
    /**
     * 添加论坛版块
     *
     * @param forumWrapper
     * @return
     */
    public ReturnCommonResult addForumTheme(ForumThemeWrapper forumThemeWrapper);

    /**
     * 查询所有的论坛版块
     * @return
     */
    public ReturnCommonResult findForumThemeByPage(int pageIndex,int dataSize);

}
