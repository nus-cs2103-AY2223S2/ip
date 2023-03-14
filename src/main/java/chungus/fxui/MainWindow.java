package chungus.fxui;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import chungus.NonBlockingUi;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane implements NonBlockingUi {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private Image chungusImage = new Image(getClass().getResourceAsStream("/images/chungus.png"));
    private Image dogeImage = new Image(getClass().getResourceAsStream("/images/doge.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(Consumer<String> inputHandler, Runnable beforeEach, Runnable afterEach,
            AtomicBoolean isRunning) {
        Runnable handleInput = () -> {
            String cmd = userInput.getText();
            if (cmd.length() == 0) {
                return;
            }
            userInput.clear();

            dialogContainer.getChildren().add(new DialogBox(cmd, dogeImage));

            beforeEach.run();
            inputHandler.accept(cmd);
            afterEach.run();
        };

        sendButton.setOnMouseClicked(event -> handleInput.run());
        userInput.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                handleInput.run();
            }
        });
    }

    @Override
    public void print(String msg, Object... args) {
        String reply = String.format(msg, args);
        dialogContainer.getChildren().add(new DialogBox(chungusImage, reply));
    }

    @Override
    public void info(String msg, Object... args) {
        print(msg, args);
    }

    @Override
    public void error(String msg, Object... args) {
        print(msg, args);
    }
}
