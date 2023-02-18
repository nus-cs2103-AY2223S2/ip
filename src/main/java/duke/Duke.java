package duke;

import duke.io.input.ui.ChatBot;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;


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
