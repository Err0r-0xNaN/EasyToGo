package cc.nanoic.servereasytogo.controller;

import cc.nanoic.servereasytogo.common.Result;
import cc.nanoic.servereasytogo.entity.Redis_VerifyCode;
import cc.nanoic.servereasytogo.entity.User;
import cc.nanoic.servereasytogo.exception.ServiceException;
import cc.nanoic.servereasytogo.service.UserService;
import cc.nanoic.servereasytogo.utils.TokenUtils;
import cn.hutool.core.date.DateTime;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

    @GetMapping("/")//测试连接通畅性(预留)
    public Result PING(){
        return Result.success();
    }

    @Autowired
    private JavaMailSenderImpl mailSender;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    //TODO: 验证邮箱 - OK
    @PostMapping("/checkEmail")
    public Result checkEmail(@RequestBody User user){
        if(user.getEmail()!=null){
            //生成四位数验证码
            Random random = new Random();
            int VerifyCode = random.nextInt(9000) + 1000;
            //存入redis，设置5分钟过期
            ValueOperations<String,String> operations = stringRedisTemplate.opsForValue();
            operations.set(user.getEmail(), String.valueOf(VerifyCode), 5, TimeUnit.MINUTES);

            //生成默认密码
                //设置密码可选字符：
            String KeyLib = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqretuvwxyz1234567890";
                //设置密码长度：(100位随机字符+数字)
            int l = 100;
            StringBuffer stringBf = new StringBuffer();
            String temp_pswd = "";
                //生成密码后缀
            for(int i = 0;i<l;i++){
                temp_pswd += KeyLib.charAt(random.nextInt(KeyLib.length()));
            }
            /*System.out.println(temp_pswd);*/
            String pswd = "Pswd_" + temp_pswd;

            user.setPassword(pswd);

            //用户首次获取验证码时记录用户信息
            if(userService.selectIdByEmail(user.getEmail()) == null){
                try{
                    userService.register_checkEmail(user);
                } catch (Exception e){
                    return Result.error(Result.CODE_SQL_CONNECT_ERROR, "数据库异常!");
                }
            }

            //发送验证码至邮箱
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("2026256242@qq.com");
            simpleMailMessage.setTo(user.getEmail());
            simpleMailMessage.setSubject("您的邮箱验证码");
            simpleMailMessage.setText("您本次邮箱验证的验证码是：" + VerifyCode + " \r\n感谢您注册E通达，祝您学习生活愉快~");
            mailSender.send(simpleMailMessage);
            return Result.success("验证码已发送!");
        }
        else {
            return Result.error("邮箱不能为空!");
        }
    }

    //TODO: 注册(更新邮箱激活状态)
    @PostMapping("/register")
    public Result register(@RequestBody Redis_VerifyCode verifyCode){
        if(verifyCode.getEmail()!=null && verifyCode.getVerifyCode()!=null) {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            if(Objects.equals(String.valueOf(verifyCode.getVerifyCode()), operations.get(verifyCode.getEmail()))){
                try{
                    User user = new User();
                    user.setEmail(verifyCode.getEmail());
                    user.setPassword(verifyCode.getPassword());
                    user.setRegisterDate(DateTime.now());
                    user.setUsername("USER_" + userService.selectLastId());
                    userService.register_changeStatus(user);
                } catch (Exception e){
                    return Result.error("数据库写入失败!请联系管理员查看后台日志![当前时间:"+DateTime.now()+"]");
                }
                return Result.success();
            }
            else{
                return Result.error("验证码有误!");
            }

        }
        else{
            return Result.error("邮箱或验证码不能为空!");
        }
    }

    //TODO: 登录
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        User dbUser = userService.selectUserInfoByEmail(user);
        if(dbUser == null){
            throw new ServiceException("邮箱或密码错误!");
        }
        else if(!user.getPassword().equals(dbUser.getPassword())){
            throw new ServiceException("邮箱或密码错误!");
        }
        String token = TokenUtils.createToken(dbUser.getUuid().toString(),dbUser.getPassword());

        dbUser.setToken(token);
        dbUser.setPassword("ThisIsThePassword,ButForSecurityReasons,YouCannotViewIt!^!");
        dbUser.setEmailActived(1919810);

        return Result.success(dbUser);
    }

    //TODO: 管理员登陆


    //TODO: 查询菜单




    //TODO: 查询用户总数


    //TODO: 查询所有用户信息



}
