package duke;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;
    private Main main;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image erenImage = new Image(this.getClass().getResourceAsStream("/images/Eren.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the current instance of duke and main for this class.
     *
     * @param duke current instance of duke.
     * @param main current instance of main.
     */
    public void setMain(Duke duke, Main main) {
        this.main = main;
        this.duke = duke;
    }

    /**
     * Creates one dialog box echoing user input and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        for (Node node:dialogContainer.getChildren()){
            VBox.setVgrow(node,Priority.ALWAYS);
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        duke.processInput(input);
        userInput.clear();
    }

    /**
     * Creates a dialog box containing Duke's reply and then appends them to
     * the dialog container.
     *
     * @param reply String that contains duke reply.
     */
    public void sendDukeResponse(String reply) {
        if (reply != "") {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(reply, erenImage)
            );
        }
    }
}