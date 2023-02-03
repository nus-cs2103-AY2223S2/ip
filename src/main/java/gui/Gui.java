package gui;

import java.util.Objects;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Gui parallel of Ui.
 */
public class Gui {
    private static final String FAREWELL = "Bye Bye. Leave good review please! PLEAASEEE!";
    private static final String GREETING = "Hallo Hallo niece and nephew! My name is Uncle Roger."
            + "What you want?";
    private final VBox dialogContainer;
    private final TextField userInput;

    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png"))
    );
    private final Image dukeImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png"))
    );

    /**
     * GUI constructor.
     * @param dialogContainer VBox object.
     * @param userInput TextField object.
     */
    public Gui(VBox dialogContainer, TextField userInput) {
        this.dialogContainer = dialogContainer;
        this.userInput = userInput;
    }

    /**
     * Echos whatever the user says.
     * @param message Thing to say.
     */
    public void echo(String message) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(message, userImage)
        );
    }

    /**
     * Says something.
     * @param message Thing to say.
     */
    public void say(String message) {
        String[] lines = message.split("\n");
        int i = 0;
        StringBuilder finalMessage = new StringBuilder();
        while (true) {
            finalMessage.append(lines[i]).append("\n");
            i++;
            if (i == lines.length) {
                dialogContainer.getChildren().addAll(
                        DialogBox.getDukeDialog(finalMessage.toString(), dukeImage)
                );
                break;
            }
            if (i % 4 == 0) {
                dialogContainer.getChildren().addAll(
                        DialogBox.getDukeDialog(finalMessage.toString(), dukeImage)
                );
                finalMessage = new StringBuilder();
            }
        }
    }

    /**
     * Greets the user.
     */
    public void greet() {
        say(GREETING);
    }

    /**
     * Says bye to the user.
     */
    public void sayFarewell() {
        say(FAREWELL);
    }

    /**
     * Shows the error message.
     * @param errorMessage Error message to be shown.
     */
    public void showError(String errorMessage) {
        say(errorMessage);
    }
}
