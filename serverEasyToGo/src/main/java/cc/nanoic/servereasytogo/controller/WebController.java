package cc.nanoic.servereasytogo.controller;

import cc.nanoic.servereasytogo.common.Result;
import cc.nanoic.servereasytogo.entity.User;
import cc.nanoic.servereasytogo.exception.ServiceException;
import cc.nanoic.servereasytogo.service.UserService;
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

    @PostMapping("/register")
    public Result login(@RequestBody User data){
        if(data.getRegisterType().equals("uADNp")){
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
            else if(userService.selectByUsername(data.getUsername())!=null){
                return Result.error("用户名已被注册!");
            }
            else{
                userService.registerEmail(data.getUsername(), data.getPassword());
            }
        }
        else{
            throw new ServiceException("我没传这类型捏...怎么绘世呢🤔...将此错误信息截图给网站管理员");
        }

        return Result.success();

    }
}
