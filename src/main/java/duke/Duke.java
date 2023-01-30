package duke;

import duke.io.input.ui.UserInteraction;
public class Duke {
    public static void main(String[] args) {
        UserInteraction chatBot = new UserInteraction();
        chatBot.printLogo();
        chatBot.chatBegin();
    }
}
