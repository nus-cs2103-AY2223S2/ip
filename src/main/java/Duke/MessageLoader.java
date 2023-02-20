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
        return "Howdy pardner! Today is a good day to defend the base!";
    }
    public String getGoodbyeMessage(){
        return "Goodbye.";
    }
}
