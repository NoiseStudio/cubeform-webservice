package pl.platrykp.cubeformservice.resources;

public class WelcomeMessage {
    private static final String[] messages = {
            "Dobry!",
            "Pozdro z serwera",
            "Witaj!",
            "Ay!"
    };

    public String welcomeMessage;

    public WelcomeMessage(){
        int randElem = (int)(Math.random() * messages.length);
        welcomeMessage = messages[randElem];
    }
}
