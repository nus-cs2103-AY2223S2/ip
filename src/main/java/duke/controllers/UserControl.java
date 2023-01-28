package duke.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class UserControl extends StackPane {
    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    @FXML
    private ImageView sendIcon;

    private MainWindow mainWindow;

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

    @FXML
    public void sendInput() {
        String input = userInput.getText();
        this.mainWindow.handleUserInput(input);
        userInput.clear();
    }
}