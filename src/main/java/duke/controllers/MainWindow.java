package duke.controllers;

import duke.Duke;
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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * @param d initializing the duke app in the MainWindow.
     *
     */
    public void setDuke(Duke d) {
        duke = d;
        greetUser();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = duke.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (response.equals("bye")) {
//            try {
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getDukeDialog(response, dukeImage)
                );
                userInput.clear();
                System.exit(0);
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                System.out.println(e.getMessage());
//            } finally {
//
//            }
        }
    }

    /**
     * Greets the user upon launch of application.
     */
    @FXML
    public void greetUser() {
        String result = "Hello from! I'm a Cookie Monster\n" + "What can I do for you?\n";
        dialogContainer.getChildren().addAll
                (DialogBox.getDukeDialog(result, dukeImage));
    }
}