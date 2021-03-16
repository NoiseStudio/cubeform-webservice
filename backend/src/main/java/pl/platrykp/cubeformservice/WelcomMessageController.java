package pl.platrykp.cubeformservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomMessageController {
    private static final String[] messages = {
            "Dobry!",
            "Pozdro z serwera",
            "Witaj!",
            "Ay!"
    };

    @GetMapping("/welcomeMessage")
    public String getWelcomeMessage(){
        int randElem = (int)(Math.random() * messages.length);
        String msg = messages[randElem];
        return "{\"welcomeMessage\":\""+msg+"\"}";
    }

}
