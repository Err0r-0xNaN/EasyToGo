package cc.nanoic.servereasytogo.test;

import cc.nanoic.servereasytogo.common.AuthAccess;
import cc.nanoic.servereasytogo.common.Result;
import cc.nanoic.servereasytogo.entity.User;
import cc.nanoic.servereasytogo.service.UserService;
import cn.hutool.core.date.DateTime;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:Api接口测试
 * @Author: Nanoic
 * @Date: 2023-12-25
 * @FileName: ApiTestController
 **/
@RestController
public class ApiTestController {
    @Resource
    UserService userService;

    /*@GetMapping("/")
    public Result PING(){
        return Result.success();
    }*/

    /*@AuthAccess
    @PostMapping("/test")
    public Result test(){

    }*/

    /*@Autowired
    private StringRedisTemplate stringRedisTemplate;*/


    /* 获取验证码 */
    /*@AuthAccess*/


    /*@AuthAccess*/




}
