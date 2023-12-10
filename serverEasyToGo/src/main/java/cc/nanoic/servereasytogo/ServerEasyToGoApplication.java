package cc.nanoic.servereasytogo;

import org.apache.catalina.filters.CorsFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(basePackages = "cc.nanoic.servereasytogo.mapper")
public class ServerEasyToGoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerEasyToGoApplication.class, args);
    }


}
