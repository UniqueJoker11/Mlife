package colin.app.service.mlife.service;

import colin.app.service.mlife.controller.wrapper.ForumWrapper;
import colin.app.service.mlife.core.common.ReturnCommonResult;

/**
 * Created by joker on 16/9/3.
 */
public interface ForumService {

    /**
     * 添加论坛版块
     *
     * @param forumWrapper
     * @return
     */
    public ReturnCommonResult addForum(ForumWrapper forumWrapper);

    /**
     * 查询所有的论坛版块
     * @return
     */
    public ReturnCommonResult findForumByPage(int pageIndex,int dataSize);

    /**
     * 查询所有的论坛版块
     * @return
     */
    public ReturnCommonResult findAllForum();
}
