package pl.platrykp.cubeformservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.platrykp.cubeformservice.resources.WelcomeMessage;

@RestController
public class WelcomeMessageController {

    @GetMapping(value = "/welcomeMessage")
    public WelcomeMessage getWelcomeMessage(){
        return new WelcomeMessage();
    }

}
