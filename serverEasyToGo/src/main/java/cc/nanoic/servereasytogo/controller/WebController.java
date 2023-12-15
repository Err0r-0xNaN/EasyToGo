package cc.nanoic.servereasytogo.controller;

import cc.nanoic.servereasytogo.common.Result;
import cc.nanoic.servereasytogo.entity.User;
import cc.nanoic.servereasytogo.exception.ServiceException;
import cc.nanoic.servereasytogo.mapper.UserMapper;
import cc.nanoic.servereasytogo.service.UserService;
import cc.nanoic.servereasytogo.utils.TokenUtils;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

@RestController

public class WebController {
    @Resource
    UserService userService;

    @GetMapping("/")
    public Result serverStatus(){
        return Result.success("success");
    }

    /**
     * 注册
     * @param data
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody User data){
        System.out.println("Type: -> " + data.getRegisterType() +
                "\nTypeEqual uANDp -> " + data.getRegisterType().equals("uADNp") +
                "\nTypeEqual email -> " + data.getRegisterType().equals("email"));
        if(data.getRegisterType().replaceAll("\\p{C}","").equals("uANDp")){
            if(StrUtil.isBlank(data.getUsername()) || StrUtil.isBlank(data.getPassword())){
                return Result.error("用户名或密码不能为空!");
            }
            else if(userService.selectByUsername(data.getUsername())!=null){
                return Result.error("用户名已被注册!");
            }
            else{
                userService.registerUAP(data.getUsername(), data.getPassword());
            }
        }
        else if(data.getRegisterType().equals("email")){
            if(StrUtil.isBlank(data.getUsername()) || StrUtil.isBlank(data.getPassword())){
                return Result.error("用户名或密码不能为空!");
            }
            else if(userService.selectByEmail(data.getUsername())!=null){
                return Result.error("邮箱已被注册!");
            }
            else{
                userService.registerEmail(data.getUsername(), data.getPassword());
                return Result.success(userService.selectByEmail(data.getUsername()));
            }
        }
        else{
            return Result.error(Result.CODE_TYPE_ERROR,
                    "我没传这类型捏...怎么绘世呢🤔...请将此错误信息反馈给网站管理员");
        }

        return Result.success();

    }


    @PostMapping("/login")
    public User login(@RequestBody User user){
        User dbUser = userService.selectByUsername(user.getUsername());
        if(dbUser == null){
            throw new ServiceException("用户名或密码错误!");
        }
        else if(!user.getPassword().equals(dbUser.getPassword())){
            throw new ServiceException("用户名或密码错误!");
        }
        String token = TokenUtils.createToken(dbUser.getUuid().toString(),dbUser.getPassword());

        dbUser.setToken(token);
        dbUser.setPassword("***");
        return dbUser;
    }
}
