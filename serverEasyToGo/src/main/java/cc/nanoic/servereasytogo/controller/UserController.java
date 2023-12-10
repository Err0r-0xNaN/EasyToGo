package cc.nanoic.servereasytogo.controller;

import cc.nanoic.servereasytogo.common.Result;
import cc.nanoic.servereasytogo.entity.User;
import cc.nanoic.servereasytogo.service.UserService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /* 增 */
    @PostMapping("/add")
    public Result add(@RequestBody User user){
        try{
            userService.insertUser(user);
        }catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return Result.error(Result.CODE_SQL_ERROR, "插入数据库出错!");
            } else {
                return Result.error("系统错误");
            }
        }
        return Result.success();
    }

    /* 改 */
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        try{
            userService.updateUser(user);

        }catch (Exception e) {
            return Result.error();
        }
        return  Result.success();

    }

    /* 删 */
    @DeleteMapping("/del/{uuid}")
    public Result delete(@PathVariable Integer uuid){
        try{
            userService.delete(uuid);
        }catch (Exception e){
            return Result.error(e.toString());
        }
        return Result.success();
    }

    //删批量
    @DeleteMapping("/del/batch")
    public Result delete(@RequestBody List<Integer> uuids){
        try{
            for(Integer id : uuids){
                userService.delete(id);
            }
        }catch (Exception e){
            return Result.error(e.toString());
        }
        return Result.success();
    }


    /* 查 */
    @GetMapping("/select/{username}")
    public Result selectByUsername(@PathVariable String username){
        try{
            return Result.success(userService.selectByUsername(username));
        }catch (Exception e){
            return Result.error(e.toString());
        }

    }

    //查全部
    @GetMapping("/selectAll")
    public Result selectAll(){
        try{
            return Result.success(userService.selectAll());
        }catch (Exception e){
            return Result.error(e.toString());
        }

    }
}
