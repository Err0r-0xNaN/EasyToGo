package cc.nanoic.servereasytogo.controller;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;
import java.util.Map;

@RestController
/*@RequestMapping("/")*/
public class WebController {

    @GetMapping("/login")
    @CrossOrigin()
    public String login(@RequestBody Map<String, String> data/*,@CookieValue (name = "token") String token*/){
        String username = data.get("username");

        System.out.println(username);
        return username;
    }
}
