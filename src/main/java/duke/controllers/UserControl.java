package duke.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * Controller for the footer section of the GUI where user inputs the desired commands
 * for Duke to execute.
 */
public class UserControl extends StackPane {
    /**
     * The component for user to input the desired text command.
     */
    @FXML
    private TextField userInput;

    /**
     * The button that the user clicks to send the desired command for Duke.
     */
    @FXML
    private Button sendButton;

    /**
     * The icon of the sendButton.
     */
    @FXML
    private ImageView sendIcon;

    private MainWindow mainWindow;

    /**
     * Constructor for UserControl.
     */
    public UserControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/user-control/UserControl.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
            userInput.setOnAction((event) -> {
                sendInput();
            });
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Initialises the UserControl controller.
     */
    @FXML
    public void initialize() {
        Image sendIcon = new Image(this.getClass().getResourceAsStream("/images/send.png"));
        this.sendIcon.setImage(sendIcon);
        userInput.setOnAction((event) -> {
            sendInput();
        });
        sendButton.setOnAction((event) -> {
            sendInput();
        });
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Handler to execute when user clicks on sendButton to post the desired text command to Duke.
     */
    @FXML
    public void sendInput() {
        String input = userInput.getText();
        this.mainWindow.handleUserInput(input);
        userInput.clear();
    }
}
