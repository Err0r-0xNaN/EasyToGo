package cc.nanoic.servereasytogo.controller;

import cc.nanoic.servereasytogo.common.Result;
import cc.nanoic.servereasytogo.entity.User;
import cc.nanoic.servereasytogo.exception.ServiceException;
import cc.nanoic.servereasytogo.mapper.UserMapper;
import cc.nanoic.servereasytogo.service.UserService;
import cc.nanoic.servereasytogo.utils.TokenUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

/**
 * @Description:控制器
 * @Author: Nanoic
 * @Date: 2023-12-25
 * @FileName: WebController
 **/
@RestController
public class WebController {
    @Resource
    UserService userService;

    @Autowired
    private JavaMailSenderImpl mailSender;

    /* 验证邮箱 - OK */
    @PostMapping("/checkEmail")
    public Result checkEmail(@RequestBody User user) {
        if (user.getEmail().isBlank()) {
            return Result.error("邮箱不能为空!");
        }
        if (user.getEmail().contains("#") || user.getEmail().contains("--")) {
            return Result.error(Result.CODE_SYS_ERROR, "邮箱内不能含有危险符号");
        }
        User dbUser = userService.selectByEmail(user);
        if (dbUser != null) {//已有数据
            if (dbUser.getEmailActived() != null) {//二重验证，防止我修改数据库导致系统异常
                if (dbUser.getEmailActived() == 1) {
                    return Result.error("邮箱已被注册! ");
                }
            }
            try {
                Random random = new Random();
                int randomNumber = random.nextInt(9000);
                user.setEmailVerifyCode(randomNumber);
                user.setEmailVerifyCode(1000 + randomNumber);
                userService.updateVerifyCode(user);//更新验证码

                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setFrom("2026256242@qq.com");
                simpleMailMessage.setTo(user.getEmail());
                simpleMailMessage.setSubject("您的邮箱验证码");
                simpleMailMessage.setText("您本次邮箱验证的验证码是：" +
                        user.getEmailVerifyCode() +
                        " \r\n感谢您注册E通达，祝您学习生活愉快~");
                mailSender.send(simpleMailMessage);

                return Result.success("验证码已刷新!");
            } catch (ServiceException e) {
                return Result.error(Result.CODE_SQL_CONNECT_ERROR, e.getMessage());
            }
        } else {
            if (userService.selectAll() != null) {
                //数据不存在
                user.setUsername("User_0");//设置用户名
            } else {
                user.setUsername("User_" + (userService.selectAll().size() + 1));
            }
            user.setPassword("123456");//设置默认密码
            // 生成一个0到8999之间的随机数
            Random random = new Random();
            int randomNumber = random.nextInt(9000);
            user.setEmailVerifyCode(randomNumber);
            user.setEmailVerifyCode(1000 + randomNumber);
            try {
                userService.register(user);//注册

                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setFrom("2026256242@qq.com");
                simpleMailMessage.setTo(user.getEmail());
                simpleMailMessage.setSubject("您的邮箱验证码");
                simpleMailMessage.setText("您本次邮箱验证的验证码是：" +
                        user.getEmailVerifyCode() +
                        " \r\n感谢您注册E通达，祝您学习生活愉快~");
                mailSender.send(simpleMailMessage);

                return Result.success("验证码已发送");
            } catch (ServiceException e) {
                return Result.error(Result.CODE_SQL_CONNECT_ERROR, e.getMessage());
            }

        }
    }

    /* 注册(更新邮箱激活状态) - OK */
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        if (user.getEmailVerifyCode() == null) {
            return Result.error("验证码不能为空!");
        }
        else {
            if (userService.selectByEmail(user) != null) {
                if (userService.selectByEmail(user).getEmailActived() == 1) {
                    return Result.error("该邮箱已激活!");
                }
                else {
                    if (user.getEmailVerifyCode().equals(userService.selectByEmail(user).getEmailVerifyCode())) {
                        java.util.Date date = new Date();
                        user.setRegisterDate(date);
                        userService.updateVerifyActive(user);
                        return Result.success();
                    }
                    else {
                        return Result.error("验证码有误!");
                    }
                }
            }
        }
        return Result.success();
    }

    /* 登录 - OK */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        User dbUser = userService.selectByEmail(user);
        if(dbUser == null){
            throw new ServiceException("邮箱或密码错误!");
        }
        else if(!user.getPassword().equals(dbUser.getPassword())){
            throw new ServiceException("邮箱或密码错误!");
        }
        String token = TokenUtils.createToken(dbUser.getUuid().toString(),dbUser.getPassword());

        dbUser.setToken(token);
        dbUser.setPassword("ThisIsThePassword,ButForSecurityReasons,YouCannotViewIt!^!");
        dbUser.setEmailVerifyCode(114514);
        dbUser.setEmailActived(1919810);

        return Result.success(dbUser);
    }



}
