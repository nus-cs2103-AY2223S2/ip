package duke.controller;

import java.io.IOException;

import duke.driver.GuiDriver;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    private Image userImage =
            new Image(getClass().getResourceAsStream("/resources/images/Duke.png"));
    private Image dukeImage =
            new Image(getClass().getResourceAsStream("/resources/images/Duke.png"));


    /**
     * MainWindow Controller which acts as the layout for other controllers.
     */
    public MainWindow() {
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(getClass().getResource("/resources/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Intializes the controller by adding the greet dialog to the chat and setting the height
     * property.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        greet();
    }

    private void greet() {
        DialogBox dukeBox =
                DialogBox.getDukeDialog("Hello! I'm Duke\nWhat can I do for you?", dukeImage);
        dialogContainer.getChildren().addAll(dukeBox);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {

        // Get input from user
        String inputString = userInput.getText();

        // Process the input
        String response = GuiDriver.getResponse(inputString);

        // Create DialogBox to place what the user has typed and Duke's response
        DialogBox userBox = DialogBox.getUserDialog(inputString, userImage);
        DialogBox dukeBox = DialogBox.getDukeDialog(response, dukeImage);
        dialogContainer.getChildren().addAll(userBox, dukeBox);

        // Reset user input
        userInput.clear();

        // Terminate program if user typed 'bye'
        exitIfByeCommand(response);
    }

    private void exitIfByeCommand(String response) {
        boolean isByeCommand = response.equals("Bye. Hope to see you again soon!");
        if (isByeCommand) {
            Platform.exit();
        }
    }
}
