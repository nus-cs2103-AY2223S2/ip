package duke;

import duke.io.input.ui.ChatBot;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;


public class Duke {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image duke = new Image(this.getClass().getResourceAsStream("/images/ArnimZola.jpg"));
    private Image user = new Image(this.getClass().getResourceAsStream("/images/Vader.jpg"));


    /**
     * Initialize Duke chatbot and begin chatting with user.
     *
     * @param args
     */
    public static void main(String[] args) {
        ChatBot chatBot = new ChatBot();
        chatBot.printLogo();
        chatBot.chatBegin();
    }


}
