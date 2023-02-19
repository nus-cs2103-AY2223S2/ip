package Duke;

/**
 * Class to load messages from files into strings
 * @author Bryan Juniano
 */

public class MessageLoader {

    protected final static String PATH = "./data/messages";
    protected final static String WELCOME_NAME = "/welcome.txt";
    protected final static String GOODBYE_NAME = "/goodbye.txt";

    public String getWelcomeMessage(){
        return "Welcome to the basement! Type help in the box below to get started.";
    }
    public String getGoodbyeMessage(){
        return "Goodbye.";
    }
}
