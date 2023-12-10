package cc.nanoic.servereasytogo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyCORSConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // 允许的来源域名，可以配置多个，使用逗号分隔
                registry.addMapping("/**")
                        .allowedOrigins("*","/*","/**")
                        // 允许的请求方法，可以配置多个，使用逗号分隔
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        // 允许的请求头，可以配置多个，使用逗号分隔
                        .allowedHeaders("Content-Type", "X-Requested-With")
                        // 预检请求的有效期（单位：秒）
                        .maxAge(3600);
            }
        };
    }
}
