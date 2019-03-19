package pl.dabrowski.demoapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldEndPoint {

    //localhost:8080/hello
    //Hello World
    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    String hello(){
        return "Hello Heroku Hej!";
    }

}
