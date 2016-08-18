package colin.app.service.mlife.core.dao;

import colin.app.service.mlife.core.pojo.User;
import com.mongodb.QueryBuilder;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/8/18.
 */
@Repository
public class UserDao {
    private static final String USER_COLLECTIONS="users";
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 用户是否存在
     * @param username
     * @return
     */
    public boolean isUserExists(String username){
        Query userQuery= Query.query(new Criteria("username").is(username));
        long count=mongoTemplate.count(userQuery, User.class);
        return count>0?true:false;
    }

    /**
     * 验证用户信息
     * @param username
     * @param password
     * @return
     */
    public User authUserInfo(String username,String password){
        Query userQuery=Query.query(new Criteria("username").is(username)).addCriteria(new Criteria("password").is(password));
        List<User> users=mongoTemplate.find(userQuery,User.class);
        return null==users||users.isEmpty()?null:users.get(0);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    public void addUser(User user){
        mongoTemplate.insert(user);
    }

    /**
     * 批量插入用户
     * @param users
     */
    public void addUsers(List<User> users){
        mongoTemplate.insert(users,User.class);
    }
}
