package duke.gui;

import duke.Bot;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Bot bot;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user-profile.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/ipman-profile.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBot(Bot b) {
        this.bot = b;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isBlank()) {
            return;
        }

        String response = bot.process(input).getResponse();
        dialogContainer.getChildren().addAll(
               DialogBox.getUserDialog(input, userImage),
               DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
