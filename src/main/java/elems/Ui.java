package elems;

import duke.DialogBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * Represents the User Interface that takes in input and displays output to the user
 * @author clydelhui
 */
public class Ui {

    private static final String logo = " ____        _        \n"
                              + "|  _ \\ _   _| | _____ \n"
                              + "| | | | | | | |/ / _ \\\n"
                              + "| |_| | |_| |   <  __/\n"
                              + "|____/ \\__,_|_|\\_\\___|\n";

    private final VBox dialogContainer;
    private final Image userImage;
    private final Image dukeImage;
    private final TextField userInput;

    /**
     * Creates a new Ui given the JavaFX elements
     * @param dialogContainer A container to store the dialog elements
     * @param userImage The image that represents the User's avatar
     * @param dukeImage The image that represents Duke's avatar
     * @param userInput The text box which the user uses to key in text to the program
     */
    public Ui(VBox dialogContainer, Image userImage, Image dukeImage, TextField userInput) {
        this.dialogContainer = dialogContainer;
        this.userImage = userImage;
        this.dukeImage = dukeImage;
        this.userInput = userInput;
    }

    /**
     * Displays the given text as a message from Duke
     * @param displayText The text to be displayed
     */
    public void dukeDisplay(String displayText) {
        Label dukeText = new Label(displayText);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeImage))
        );
    }

    public String getInput() {
        String input = userInput.getText();
        Label userText = new Label(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage))
        );
        userInput.clear();
        return input;
    }

    public void welcome() {
        dukeDisplay("Hello from\n" + Ui.logo);
    }

    public void errorDisplay(Exception exception) {
        dukeDisplay(exception.getMessage());
    }

}
