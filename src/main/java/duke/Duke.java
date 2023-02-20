package duke;

import duke.io.input.ui.ChatBot;

public class Duke {
    /**
     * Initialize Duke chatbot and begin chatting with user.
     *
     * @param args
     */
    public static void main(String[] args) {
        ChatBot chatBot = new ChatBot();
        chatBot.greetUser();
        chatBot.beginChat();
        chatBot.endChat();
    }
}
