package cc.nanoic.servereasytogo.service;

import cc.nanoic.servereasytogo.common.Result;
import cc.nanoic.servereasytogo.entity.User;
import cc.nanoic.servereasytogo.exception.ServiceException;
import cc.nanoic.servereasytogo.mapper.UserMapper;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

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

    public void registerUAP(String username, String password) {
        String userName = "";
        String passWord = password;
        User userInfo = userMapper.selectByUsername(userName);
        if(userInfo == null){
            //数据库插入用户数据
            /*userMapper.insert();*/
        }
        else{
            throw new ServiceException("用户已存在");
        }
    }

    public User login(User user){
        User userInfo = userMapper.selectByUsername(user.getUsername());
        if(userInfo == null){
            throw new ServiceException("用户不存在");
        } else if(!user.getPassword().equals(userInfo.getPassword())){
            throw new ServiceException("用户名或密码错误!");
        }
        else{
            return userInfo;
        }
    }

    public void registerEmail(String username, String password) {
        User userInfo = userMapper.selectByEmail(username);
    }
}
