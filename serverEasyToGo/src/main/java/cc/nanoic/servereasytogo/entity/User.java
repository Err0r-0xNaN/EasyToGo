package cc.nanoic.servereasytogo.entity;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description:User表实体
 * @Author: Nanoic
 * @Date: 2023-12-25
 * @FileName: User
 **/
@Data
public class User {
    private Integer uuid;
    private String username;
    private String nickname;
    private String sex;
    private String password;
    private String avatar;//头像(路径)
    private String email;//邮箱
    private Integer emailActived;//邮箱激活状态

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date registerDate;//注册时间

    private String intro;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    private String token;

    //预留
    private String registerType;


}
