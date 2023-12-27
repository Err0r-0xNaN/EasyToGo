package cc.nanoic.servereasytogo.entity;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description:User表映射
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
    private String avatar;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date registerDate;
    private String email;
    private Integer emailActived;
    private Integer emailVerifyCode;
    private String registerType;
    private String token;

}
