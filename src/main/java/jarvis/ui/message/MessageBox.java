package jarvis.ui.message;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Container class for a chat message.
 */
public class MessageBox extends HBox {
    private final Label messageText;
    private final ImageView displayPicture;

    /**
     * Constructor for a message box.
     *
     * @param messageText Message label.
     * @param displayPicture Display picture image view.
     */
    public MessageBox(Label messageText, ImageView displayPicture) {
        this.messageText = messageText;
        this.displayPicture = displayPicture;

        this.messageText.setWrapText(true);
        this.displayPicture.setFitHeight(50);
        this.displayPicture.setFitWidth(50);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(this.messageText, this.displayPicture);
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
}
