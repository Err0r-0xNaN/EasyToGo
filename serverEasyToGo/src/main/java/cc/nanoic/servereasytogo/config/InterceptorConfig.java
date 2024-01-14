package cc.nanoic.servereasytogo.config;

import cc.nanoic.servereasytogo.common.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Description:身份认证，防止越权访问
 * @Author: Nanoic
 * @Date: 2023-12-25
 * @FileName: InterceptorConfig
 **/
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/checkEmail", "/register", "/login", "/", "/consoleLogin", "/adminLogin");
        super.addInterceptors(registry);
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
}