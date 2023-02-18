package gui;

import duke.Duke;
import duke.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private Ui userInterface;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/CaptainCTF.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duuk.png"));

    /**
     * This method sets up the JavaFX controller and performs the following operations:
     * Binds the scrollPane to the dialogContainer's heightProperty.
     * Creates a new Ui instance to handle user interface interactions.
     * Displays a welcome message from the Ui.
     * Adds the Duke dialog containing the welcome message to the dialogContainer.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.userInterface = new Ui();
        String response = this.userInterface.getWelcomeReply();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Generates a pair of dialogue boxes: one box displays the user's input, while the other one shows Duke's response.
     * These dialog boxes are then appended to the dialog container.
     * The method clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        userInterface.closeWindow(response);

    }
}
