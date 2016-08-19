package colin.app.service.mlife.test.dao;

import colin.app.service.mlife.core.dao.UserDao;
import colin.app.service.mlife.core.pojo.User;
import colin.app.service.mlife.test.CommonTestConfig;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by Administrator on 2016/8/18.
 */
@RestClientTest({UserDao.class,MongoTemplate.class})
public class TUserDao extends CommonTestConfig{
    @Autowired
    UserDao userDao;
    @Test
    public void addUser(){
      userDao.addUser(initaddUser());
    }

    /**
     * 初始化用户数据
     * @return
     */
    private User initaddUser(){
        User user=new User();
        user.setUsername("colin");
        user.setNickname("joker");
        user.setPassword(DigestUtils.sha1Hex("123456"));
        user.setAddress("China zhejiang hangzhou");
        user.setSex(1);
        return user;
    }
}
