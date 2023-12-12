package cc.nanoic.servereasytogo.mapper;

import cc.nanoic.servereasytogo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserMapper {

    //目前仅支持插入用户名、密码，后期有需要拓展
    /*插入数据*/
    @Insert("insert into `user` (username, password) " +
            "values (#{username}, #{password})")
    void insert(User user);



    /*@Select("")*/
    @Update("update `user` set nickname = #{nickname}, " +
            "sex = #{sex}, password = #{password}, " +
            "avatar = #{avatar}, email = #{email} " +
            "where uuid = #{uuid}")
    void update(User user);

    @Delete("delete from `user` where uuid = #{uuid}")
    void delete(Integer uuid);

    /* 查个人 */


    /* 查全部 */
    @Select("select * from `user` order by uuid desc")
    List<User> selectAll();

    @Select("select * from `user` where username = #{username}")
    User selectByUsername(String username);

    @Select("select * from `user` where email = #{username}")
    User selectByEmail(String username);
}
