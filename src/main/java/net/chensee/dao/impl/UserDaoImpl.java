package net.chensee.dao.impl;

import net.chensee.dao.UserDao;
import net.chensee.entity.po.UserLoginPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public UserLoginPo findUser(String username, String password) {
        Query query = new Query(Criteria.where("username").is(username)
                .and("password").is(password));
        return mongoTemplate.findOne(query, UserLoginPo.class);
    }

    @Override
    public UserLoginPo findUserById(String userId) {
        Query query = new Query(Criteria.where("id").is(userId));
        return mongoTemplate.findOne(query, UserLoginPo.class);
    }

    @Override
    public List<UserLoginPo> getUsers(int pageNum, int pageSize) {
        Query query = new Query();
        query.skip((pageNum - 1) * pageSize).limit(pageSize);
        return mongoTemplate.find(query, UserLoginPo.class);
    }

    @Override
    public void addUser(UserLoginPo userLoginPo) {
        mongoTemplate.insert(userLoginPo);
    }

    @Override
    public void delUser(String userId) {
        Query query = new Query(Criteria.where("id").is(userId));
        mongoTemplate.remove(query, UserLoginPo.class);
    }

    @Override
    public long getUsersCount() {
        return mongoTemplate.count(new Query(), UserLoginPo.class);
    }
}
