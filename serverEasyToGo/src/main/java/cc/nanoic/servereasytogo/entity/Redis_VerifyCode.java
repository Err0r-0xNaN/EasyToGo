package cc.nanoic.servereasytogo.entity;

import lombok.Data;

/**
 * @Description: 邮箱-验证码 实体
 * @Author: Nanoic
 * @Date: 2024-01-14
 * @FileName: Redis_VerfiyCode
 **/

@Data
public class Redis_VerifyCode {

    private String email;
    private Integer verifyCode;
    private String password;
}
