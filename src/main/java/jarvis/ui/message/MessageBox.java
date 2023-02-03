package jarvis.ui.message;

import java.io.IOException;

import jarvis.ui.layout.MainWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Container class for a chat message.
 */
public class MessageBox extends HBox {
    @FXML
    private VBox messageChat;
    @FXML
    private Label messageText;
    @FXML
    private Circle displayPicture;

    /**
     * Constructor for a message box.
     *
     * @param message Message to display.
     * @param image Display picture.
     */
    public MessageBox(String message, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MessageBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        messageText.setText(message);
        displayPicture.setFill(new ImagePattern(image));
    }

    /**
     * Initialises MessageBox.
     */
    @FXML
    public void initialize() {
        this.getStyleClass().add("message-box");
        messageChat.getStyleClass().addAll("message-chat-base");
    }

    /**
     * Flips the rtl direction.
     *
     * @return This message box.
     */
    public MessageBox flip() {
        this.setAlignment(this.getAlignment() == Pos.TOP_RIGHT ? Pos.TOP_LEFT : Pos.TOP_RIGHT);
        ObservableList<Node> nodes = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(nodes);
        this.getChildren().setAll(nodes);
        return this;
    }

    /**
     * Sets the error style.
     *
     * @return This message box.
     */
    public MessageBox makeError(boolean hasError) {
        if (hasError) {
            messageChat.getStyleClass().add("message-chat-error");
        }
        return this;
    }

    /**
     * Sets the Jarvis style.
     *
     * @return This message box.
     */
    public MessageBox makeJarvis() {
        messageChat.getStyleClass().add("message-chat-jarvis");
        return this;
    }

    /**
     * Sets the Ultron style.
     *
     * @return This message box.
     */
    public MessageBox makeUltron() {
        messageChat.getStyleClass().add("message-chat-ultron");
        return this;
    }
}
