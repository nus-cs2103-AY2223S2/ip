package duke;

import duke.io.input.ui.UserInteraction;
public class Duke {

    /**
     * Initialize Duke chatbot and begin chatting with user.
     *
     * @param args
     */
    public static void main(String[] args) {
        UserInteraction chatBot = new UserInteraction();
        chatBot.printLogo();
        chatBot.chatBegin();
    }
}
