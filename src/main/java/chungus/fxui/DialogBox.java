package chungus.fxui;

import java.io.IOException;
import java.util.Collections;

import chungus.ChungusException;
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
import javafx.scene.text.TextAlignment;

class DialogBox extends HBox {
    @FXML
    private Label text;

    @FXML
    private ImageView displayPicture;

    public DialogBox(String text, Image image) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ChungusException("failed to load fxml for DialogBox", e);
        }

        this.text.setText(text);
        this.displayPicture.setImage(image);
    }

    public DialogBox(Image image, String text) {
        this(text, image);
        flip();
        this.text.setTextAlignment(TextAlignment.LEFT);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the
     * right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }
}