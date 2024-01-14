package cc.nanoic.servereasytogo.mapper;

import cc.nanoic.servereasytogo.entity.User;
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

    @Select("SELECT * FROM `userinfo` WHERE uuid = #{uuid}")
    User selectById(@Param("uuid") Integer uuid);

    /* 写入默认数据 */
    @Insert("insert into `userinfo` (password, email) value (#{password}, #{email})")
    void register_checkEmail(User user);

    //查询用户数量
    @Select("select uuid from `userinfo` order by uuid desc")
    Integer selectLastId();

    @Update("update `userinfo` set emailActived = 1, username = #{username}, password = #{password}, registerDate = #{registerDate} where email = #{email}")
    void register_changeStatus(User user);

    @Select("select uuid from `userinfo` where email = #{email}")
    Integer selectIdByEmail(String email);

    @Select("select * from `userinfo` where email = #{email}")
    User selectUserInfoByEmail(User user);

}
