package cc.nanoic.servereasytogo.service;

import cc.nanoic.servereasytogo.entity.User;
import cc.nanoic.servereasytogo.exception.ServiceException;
import cc.nanoic.servereasytogo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:User表Service
 * @Author: Nanoic
 * @Date: 2023-12-25
 * @FileName: UserService
 **/
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User selectById(User user) {
        return userMapper.selectById(user.getUuid());
    }


    public void register_checkEmail(User user) {
        userMapper.register_checkEmail(user);
    }

    public Integer selectLastId() {
        return userMapper.selectLastId();
    }

    public void register_changeStatus(User user) {

        userMapper.register_changeStatus(user);
    }

    public Integer selectIdByEmail(String email) {
        return userMapper.selectIdByEmail(email);
    }

    public User selectUserInfoByEmail(User user) {
        return userMapper.selectUserInfoByEmail(user);
    }


}
