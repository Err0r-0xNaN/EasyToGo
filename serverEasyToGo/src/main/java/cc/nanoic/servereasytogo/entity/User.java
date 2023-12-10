package cc.nanoic.servereasytogo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Data
public class User {
    private Integer uuid;
    private String username;
    private String nickname;
    private String sex;
    private String password;
    private String avatar;
    private Date registerTime;
    private String email;



}
