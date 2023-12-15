package cc.nanoic.servereasytogo.common;

import cc.nanoic.servereasytogo.entity.User;
import cc.nanoic.servereasytogo.exception.ServiceException;
import cc.nanoic.servereasytogo.mapper.UserMapper;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.server.HttpServerRequest;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String token = request.getHeader("token");
        if(StrUtil.isBlank(token)){
            token = request.getParameter("token");
        }
        if(StrUtil.isBlank(token)){
            throw new ServiceException("401", "请先登录! [错误码：Jx01]");
        }
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        }catch (JWTDecodeException j){
            throw new ServiceException("401", "JWT解码失败! [错误码：Jx02]");
        }
        User user = userMapper.selectById(userId);
        if(user == null){
            throw new ServiceException("401", "JWT异常! [错误码：Jx03]");
        }
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try{
            jwtVerifier.verify(token);//验证Token
        } catch (JWTVerificationException j){
            throw new ServiceException("401", "JWT异常! [错误码：Jx04] 0xNaNFlag{th1s_i5_jwt_err0r}");
        }
        return true;
    }


}
