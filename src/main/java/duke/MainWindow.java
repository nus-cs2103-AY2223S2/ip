package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * @author Muhammad Reyaaz
 * @version %I% %G%
 * @see Task
 * @since 11
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Duke duke;

    private final List<Timeline> recurResponse = new ArrayList<>();

    private final Image userImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Khabib.png")));
    private final Image dukeImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Ronaldo.png")));

    private final Image image = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/Wallpaper.png")));
    private final BackgroundSize backgroundSize = new BackgroundSize(1, 1, false, false, false, false);
    private final BackgroundImage backgroundImage =
            new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                    BackgroundPosition.CENTER, backgroundSize);
    private final Background background = new Background(backgroundImage);

    /**
     * Runs at the start of the javafx lifecycle.
     * The greetings message is done at initialize so that once the user launches the chatbot, it will always be shown.
     */
    @FXML
    public void initialize() {
        setScroll();
        displayWelcome();
        duke = new Duke();
    }

    /**
     * Sets the vertical value of the scroll pane to the height of the dialog container.
     */
    void setScroll() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Initializes and displays the welcome message of Duke in the dialog container.
     */
    void displayWelcome() {
        ByteArrayOutputStream newPrintStream = setNewPrintStream();
        PrintStream oldPrintStream = System.out;
        initUiGreet();
        resetPrintStream(oldPrintStream);
        String greeting = newPrintStream.toString();
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(greeting, dukeImage));
    }

    /**
     * Redirects the standard output stream to a ByteArrayOutputStream for storing the message.
     * @return The ByteArrayOutputStream instance.
     */
    ByteArrayOutputStream setNewPrintStream() {
        ByteArrayOutputStream storeString = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(storeString);
        System.setOut(printStream);
        return storeString;
    }

    /**
     * Resets the standard output stream back to its original value.
     * @param oldPrintStream The original PrintStream instance.
     */
    void resetPrintStream(PrintStream oldPrintStream) {
        System.out.flush();
        System.setOut(oldPrintStream);
    }

    /**
     * Initializes the user interface instance and shows the welcome message.
     */
    void initUiGreet() {
        Ui ui = new Ui("Greetings");
        ui.showWelcome();
    }

    /**
     * Sets the Duke instance to be used for generating responses
     *  @param d The Duke instance.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Handles user input by creating two dialog boxes, one for the user input and one for Duke's response.
     */
    @FXML
    private void handleUserInput() {

        String input = userInput.getText();
        String response = duke.getResponse(input, recurResponse);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        new TaskScheduler(recurResponse, dialogContainer, dukeImage, input);

        dialogContainer.setBackground(background);

        userInput.clear();
    }
}
