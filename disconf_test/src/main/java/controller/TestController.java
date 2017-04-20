package controller;

import config.JedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/4/19.
 */
@RestController
public class TestController {

    @Autowired
    JedisConfig config;




    @RequestMapping("/test")
    public String test(){
        System.out.println(config.getHost() + "," + config.getPort());


        return "OK";
    }
}
