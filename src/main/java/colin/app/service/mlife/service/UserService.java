package colin.app.service.mlife.service;

import colin.app.service.mlife.core.common.ReturnCommonResult;

/**
 * Created by Administrator on 2016/8/18.
 */
public interface UserService {

    /**
     * 验证用户是否存在
     * @param username
     * @return
     */
    public ReturnCommonResult isUserExists(String username);

    /**
     * 验证用户
     * @param username
     * @param password
     * @return
     */
    public ReturnCommonResult authUserInfo(String username, String password);
}
