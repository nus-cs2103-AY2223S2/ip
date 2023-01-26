package jarvis.ui.layout;

import java.util.Objects;

import jarvis.Jarvis;
import jarvis.ui.message.MessageBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller class for MainWindow.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox chatContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Jarvis jarvis;

    private final Image userImage = new Image(Objects.requireNonNull(this
            .getClass()
            .getResourceAsStream("/images/Jarvis.jpg")));
    private final Image jarvisImage = new Image(Objects.requireNonNull(this
            .getClass()
            .getResourceAsStream("/images/User.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(chatContainer.heightProperty());
    }

    public void setJarvis(Jarvis jarvis) {
        this.jarvis = jarvis;
    }

    @FXML
    private void handleUserInput() {
        if (userInput.getText().isBlank() || jarvis == null) {
            return;
        }

        Label inputText = new Label(userInput.getText());
        Label jarvisText = new Label(jarvis.getResponse(userInput.getText()));
        chatContainer.getChildren().addAll(
                new MessageBox(inputText, new ImageView(userImage)),
                new MessageBox(jarvisText, new ImageView(jarvisImage)).flip()
        );
        userInput.clear();
    }
}
