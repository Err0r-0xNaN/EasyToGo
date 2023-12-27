package cc.nanoic.servereasytogo.mapper;

import cc.nanoic.servereasytogo.entity.User;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Description:User表Mapper
 * @Author: Nanoic
 * @Date: 2023-12-25
 * @FileName: UserMapper
 **/
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM `user` WHERE uuid = #{uuid}")
    User selectById(@Param("uuid") Integer uuid);

    @Select("select * from `user` where email = #{email} order by uuid")
    User selectByEmail(User user);

    @Select("select * from `user` order by uuid desc")
    List<User> selectAll();

    /* 添加数据 */
    @Insert("insert into `user` (username, password, email, emailVerifyCode) value (#{username}, #{password}, #{email}, #{emailVerifyCode})")
    void register(User user);

    /* 更新验证码 */
    @Update("update `user` set emailVerifyCode = #{emailVerifyCode} where email = #{email}")
    void updateVerifyCode(User user);


    @Update("update `user` set emailActived = 1, emailVerifyCode = 114514, password = #{password}, registerDate = #{registerDate} where email = #{email}")
    void updateVerifyActiveStatus(User user);
}
