package dudu;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
    @FXML
    private Button sendButton;

    private Dudu dudu;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image duduImage = new Image(this.getClass().getResourceAsStream("/images/Dudu.jpeg"));
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    // //@@author huanghao1998-reused
    // Solution below adapted from "https://github.com/hansstanley/ip/blob/master/src/main/java/jarvis/ui/layout/MainWindow.java"
    public void setDudu(Dudu dudu) {
        this.dudu = dudu;
        this.userInput.setText("Hi");
        this.handleUserInput();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (Dudu.hasExit()) {
            Platform.exit();
        }
        if (!input.isBlank()) {
            String response = dudu.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDuduDialog(response, duduImage)
            );
            userInput.clear();
        }
    }
}
