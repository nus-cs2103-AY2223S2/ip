package duke.gui;

import duke.Duke;
import duke.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;


/**
 * A GUI for Duke using FXML
 */
public class DukeGui extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField textInput;

    @FXML
    private Button sendButton;

    private Duke duke;
    private Ui ui = new Ui();


    private Image userAva = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeAva = new Image(this.getClass().getResourceAsStream("/images/botlogo.jpeg"));


    @FXML
    private void handleUserInput() {
        String input = textInput.getText();
        String response;
        try {
            response = duke.getResponse(input);
        } catch (IOException err) {
            response = err.getMessage();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userAva),
                DialogBox.getDukeDialog(response, dukeAva)
        );
        textInput.clear();
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        this.duke = d;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(ui.greet(), dukeAva)
        );
    }
}
