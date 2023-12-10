package cc.nanoic.servereasytogo.controller;

import cc.nanoic.servereasytogo.common.Result;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController

public class WebController {

    @RequestMapping("/login")
    public Result login(@RequestBody Map<String, Object> data){
        String username = data.get("username").toString();
        String password = data.get("password").toString();

        return Result.success("请求成功");
    }
}
