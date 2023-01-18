import java.util.*;
public class Duke {

    public static void main(String[] args) {
        WelcomeUI welcome = new WelcomeUI();
        Scanner input = new Scanner(System.in);
        ChatBot chat = new ChatBot(input, welcome);

        chat.run();
    }
}
