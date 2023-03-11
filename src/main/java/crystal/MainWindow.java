package crystal;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

//@@author Jeffry Lum-reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html

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

    private Crystal crystal;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/human.png"));
    private Image crystalImage = new Image(this.getClass().getResourceAsStream("/images/crystal.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    }

    public void setCrystal(Crystal d) {
        crystal = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getCrystalDialog(d.printWelcome(), crystalImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Crystal's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws CrystalException {
        String input = userInput.getText();
        String response = crystal.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCrystalDialog(response, crystalImage)
        );
        userInput.clear();
        // @@author francisyzy-reused
        // Reused from https://github.com/francisyzy/ip/blob/master/src/main/java/duke/gui/MainWindow.java
        // Inspiration from https://github.com/nus-cs2103-AY2223S2/forum/issues/99
        if (input.equals("bye")) {
            PauseTransition stop = new PauseTransition(Duration.seconds(1.5));

            stop.setOnFinished(event -> {
                Platform.exit();
                // Solution fromn https://stackoverflow.com/a/20489749
                System.exit(0);
            });

            // start the pause
            stop.play();
        }
    }
}
