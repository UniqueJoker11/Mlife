package colin.app.service.mlife.service.impl;

import colin.app.service.mlife.core.common.ReturnCommonResult;
import colin.app.service.mlife.core.dao.UserDao;
import colin.app.service.mlife.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/8/18.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 验证用户是否存在
     *
     * @param username
     * @return
     */
    @Override
    public ReturnCommonResult isUserExists(String username) {
        ReturnCommonResult returnCommonResult=new ReturnCommonResult(userDao.isUserExists(username));
        return returnCommonResult;
    }
}
