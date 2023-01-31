package twofive.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Collections;

public class MessageBox extends HBox {
    @FXML
    private Label messageLabel;
    @FXML
    private ImageView profilePictureView;
    private final static Circle PROFILE_PICTURE_CIRCLE = new Circle(50, 50, 25);

    private MessageBox(String message, Image profilePicture) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MessageBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        messageLabel.setText(message);
        messageLabel.setWrapText(true);
        profilePictureView.setImage(profilePicture);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static MessageBox getUserMessageBox(String message, Image profilePicture) {
        return new MessageBox(message, profilePicture);
    }

    public static MessageBox getTwoFiveMessageBox(String message, Image profilePicture) {
        MessageBox messageBox = new MessageBox(message, profilePicture);
        messageBox.flip();
        return messageBox;
    }
}
