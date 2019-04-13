package pl.dabrowski.demoapp.App;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import pl.dabrowski.demoapp.DemoappApplicationTests;

public class HelloWorldEndPointTest extends DemoappApplicationTests {


    @Test
    public void ShouldReturnGreetings(){
        //given
        final  String url = "http://localhost:"+ port + "/hello";
        //when
        ResponseEntity<String> response = httpClient.getForEntity(url, String.class);
        //wykonac request http na localhost:8080/hello

        //then
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
        Assertions.assertThat(response.getBody()).isEqualTo("Hello Heroku Hej!");
        //odpowiedź będzie zawierała napis "Hello World!" i kod 200
    }
}
