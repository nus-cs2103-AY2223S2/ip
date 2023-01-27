import ui.WelcomeUI;

import java.util.*;
public class Duke {

    /**
     * Main driver method which initialises the chatbot with
     * a Scanner, Welcome UI and runs it.
     *
     * @param args
     */
    public static void main(String[] args) {
        WelcomeUI welcome = new WelcomeUI();
        Scanner input = new Scanner(System.in);
        ChatBot chat = new ChatBot(input, welcome);

        chat.run();
        input.close();
    }
}
