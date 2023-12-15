package cc.nanoic.servereasytogo.utils;

import cc.nanoic.servereasytogo.entity.User;
import cc.nanoic.servereasytogo.mapper.UserMapper;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

/**
 * @Description:
 * @Author: Nanoic
 * @Date: 2023-12-15
 * @FileName: TokenUtils
 **/

@Component
public class TokenUtils {

    private static UserMapper staticUserMapper;

    @Resource
    UserMapper userMapper;

    @PostConstruct
    public void setUserService(){
        staticUserMapper = userMapper;
    }

    /**
     * 生成Token
     */

    public static String createToken(String userId, String sign) {
        return JWT.create().withAudience(userId)
                .withExpiresAt(DateUtil.offsetDay(new Date(), 7))
                .sign(Algorithm.HMAC256(sign));
    }

    /**
     * 獲取當前登錄用戶信息
     */

    public static User getCurrentUser(){
        try{
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if(StrUtil.isNotBlank(token)){
                String userId = com.auth0.jwt.JWT.decode(token).getAudience().get(0);
                return staticUserMapper.selectById(userId);
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }

}
