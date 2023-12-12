package cc.nanoic.servereasytogo.config;

/* 程序员青戈 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class MyCORSConfig {

    // 当前跨域请求最大有效时长。这里默认1天
    private static final long MAX_AGE = 24 * 60 * 60;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1 设置访问源地址
        corsConfiguration.addAllowedHeader("*"); // 2 设置访问源请求头
        corsConfiguration.addAllowedMethod("*"); // 3 设置访问源请求方法
        corsConfiguration.setMaxAge(MAX_AGE);
        source.registerCorsConfiguration("/**", corsConfiguration); // 4 对接口配置跨域设置
        return new CorsFilter(source);
    }
}
/*import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyCORSConfig {

    private static final long MAX_AGE = 24 * 60 * 60 * 30;//跨域请求最大有效时长(s)

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
}*/

