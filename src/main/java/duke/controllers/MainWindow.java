package duke.controllers;

import java.nio.file.Paths;

import duke.Duke;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends BorderPane {
    /**
     * The scroll container that contains dialogContainer.
     */
    @FXML
    private ScrollPane scrollPane;

    /**
     * The container that contains all the dialogs between Duke and the user.
     */
    @FXML
    private VBox dialogContainer;

    /**
     * The footer section where the user inputs the desired command and sends it to Duke.
     */
    @FXML
    private UserControl userControl;

    /**
     * The Duke chatbot instance for this application.
     */
    private Duke duke;

    /**
     * The image of Duke.
     */
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/mascot.png"));

    /**
     * Initialises the MainWindow controller.
     */
    @FXML
    public void initialize() {
        this.duke = new Duke(this, Paths.get(".", "data", "duke.txt"));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userControl.setMainWindow(this);
    }

    /**
     * Adds a UserDialog component to the DialogContainer component.
     * @param msg The message contained in the UserDialog component.
     */
    public void addUserDialog(String msg) {
        UserDialog dialog = new UserDialog(msg);
        dialogContainer.getChildren().add(dialog);
    }

    /**
     * Adds a DukeDialog component to the DialogContainer component.
     * @param msg The message contained in the DukeDialog component.
     */
    public void addDukeDialog(String msg) {
        DukeDialog dialog = new DukeDialog(msg, dukeImage);
        dialogContainer.getChildren().add(dialog);
    }

    /**
     * Closes the GUI application after 0.5 seconds.
     */
    public void terminate() {
        /* Wait for 0.5 seconds before closing the app */
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), e -> {
            Platform.exit();
            System.exit(0); /* close the app */
        }));

        timeline.play();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    public void handleUserInput(String input) {
        if (input.length() > 0) {
            this.duke.handleUserInput(input);
        }
    }
}
