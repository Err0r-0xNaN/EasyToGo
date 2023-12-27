package cc.nanoic.servereasytogo.controller;

import cc.nanoic.servereasytogo.common.Result;
import cc.nanoic.servereasytogo.entity.User;
import cc.nanoic.servereasytogo.service.UserService;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * @Description:Api测试接口
 * @Author: Nanoic
 * @Date: 2023-12-25
 * @FileName: ApiTestController
 **/
@RestController
public class ApiTestController {
    @Resource
    UserService userService;

    @PostMapping("/selectById")
    public Result selectById(@RequestBody User data){
        User dbUser = userService.selectById(data);
        System.out.println(userService.selectById(data));
        return Result.success(dbUser);
    }

    @GetMapping("/selectAll")
    public Result selectAll(){
        System.out.println(userService.selectAll());
        return Result.success(userService.selectAll());
    }

    @PostMapping("/selectByEmail")
    public Result selectByEmail(@RequestBody User data){
        System.out.println(userService.selectByEmail(data));
        return Result.success(userService.selectByEmail(data));
    }

    @PostMapping("/updateByEmail")
    public Result updateByEmail(@RequestBody User data){
        Random random = new Random();
        int randomNumber = random.nextInt(9000);
        data.setEmailVerifyCode(randomNumber);
        data.setEmailVerifyCode(1000 + randomNumber);
        userService.updateVerifyCode(data);//更新验证码

        return Result.success();
    }
}
