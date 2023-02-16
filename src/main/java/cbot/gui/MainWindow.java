package cbot.gui;

import cbot.Cbot;
import cbot.io.Talker;
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
    private static final Image USER_IMG = new Image(MainWindow.class.getResourceAsStream("/images/MyUser.png"));
    private static final Image CBOT_IMG = new Image(MainWindow.class.getResourceAsStream("/images/MyCbot.png"));
    private static final Image CBOT_BAD_IMG = new Image(MainWindow.class.getResourceAsStream("/images/MyCbotBad.png"));

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Cbot cbot;

    /**
     * Initializes the main window of the Cbot application.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the active Cbot.
     *
     * @param c The Cbot instance to be assigned to this application.
     */
    public void setCbot(Cbot c) {
        cbot = c;
    }

    /**
     * Displays Cbot's friendly greeting.
     *
     * @see Cbot#sayHi()
     * @see Talker#sayHi()
     */
    public void greetUser() {
        dialogContainer.getChildren().add(DialogBox.getCbotDialog(Cbot.sayHi(), CBOT_IMG));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Cbot's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     *
     * @throws InterruptedException If the exit delay is interrupted.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = cbot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, USER_IMG),
                DialogBox.getCbotDialog(response, cbot.isBad() ? CBOT_BAD_IMG : CBOT_IMG)
        );
        userInput.clear();
        exitCheck();
    }

    private void exitCheck() throws InterruptedException {
        if (cbot.isBye()) {
            Thread.sleep(250);
            Platform.exit();
        }
    }
}
