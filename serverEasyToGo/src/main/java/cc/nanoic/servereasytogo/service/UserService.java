package cc.nanoic.servereasytogo.service;

import cc.nanoic.servereasytogo.entity.User;
import cc.nanoic.servereasytogo.exception.ServiceException;
import cc.nanoic.servereasytogo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public User selectByEmail(User data) {
        return userMapper.selectByEmail(data);
    }

    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    public void register(User user) {
        try{
            userMapper.register(user);
        } catch (Exception e) {
            throw new ServiceException("写入数据库失败!");
        }
    }

    public void updateVerifyCode(User user) {
        try{
            userMapper.updateVerifyCode(user);
        } catch (Exception e) {
            throw new ServiceException("更新验证码失败!");
        }
    }

    public void updateVerifyActive(User user) {
        try{
            userMapper.updateVerifyActiveStatus(user);
        } catch (Exception e) {
            System.out.println(user.getEmail() + "：更新激活状态失败!\r\nERR0R：" + e.getMessage());
            throw new ServiceException("更新激活状态失败!请及时将后方错误信息反馈给网站管理员：" + e.getMessage());
        }
    }
}
