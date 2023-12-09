package cc.nanoic.servereasytogo;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerEasyToGoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerEasyToGoApplication.class, args);
    }
    

}
