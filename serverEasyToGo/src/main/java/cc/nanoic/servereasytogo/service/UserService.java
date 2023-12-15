package cc.nanoic.servereasytogo.service;

import cc.nanoic.servereasytogo.entity.User;
import cc.nanoic.servereasytogo.exception.ServiceException;
import cc.nanoic.servereasytogo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    /***
     * 加密功能
     *
     */


    @Autowired
    UserMapper userMapper;

    public void insertUser(User user){
        userMapper.insert(user);
    }

    public void updateUser(User user){
        userMapper.update(user);
    }

    public void delete(Integer uuid) {
        userMapper.delete(uuid);
    }

    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    /* 注册，采用 md5(base64)加密 */
    public void registerUAP(String username, String password) {
        User userInfo = userMapper.selectByUsername(username);
        if(userInfo == null){
            //数据库插入用户数据
            /*userMapper.insert();*/
            //TODO
            throw new ServiceException("注册功能//TODO");
        }
        else{
            throw new ServiceException("用户已存在");
        }
    }

    public void registerEmail(String username, String password) {
        User userInfo = userMapper.selectByEmail(username);
        System.out.println(userInfo);
    }

    public User selectByEmail(String username) {
        return userMapper.selectByEmail(username);
    }
}
