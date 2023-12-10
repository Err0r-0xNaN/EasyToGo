package cc.nanoic.servereasytogo.service;

import cc.nanoic.servereasytogo.entity.User;
import cc.nanoic.servereasytogo.mapper.UserMapper;
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
}
