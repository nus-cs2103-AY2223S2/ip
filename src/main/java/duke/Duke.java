package duke;

import duke.io.input.ui.UserInteraction;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;




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
