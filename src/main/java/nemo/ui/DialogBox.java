package nemo.ui;

import java.io.IOException;
import java.util.Collections;

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
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final String NEMO_DIALOG_COLOR = "f88d64";
    private static final String NEMO_ERROR_COLOR = "dd3d00";
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip(String color) {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        // tmp.get(1) returns triangle polygon
        tmp.get(1).setScaleX(-1);
        ((Polygon) tmp.get(1)).setFill(Color.valueOf(color));
        //tmp.get(2) returns label
        String style = String.format("-fx-background-color: #%s; -fx-padding: 8; -fx-border-radius: 5px; "
                + "-fx-background-radius: 5px; -fx-font-size: 14;", color);
        tmp.get(2).setStyle(style);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getNemoDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip(NEMO_DIALOG_COLOR);
        return db;
    }

    public static DialogBox getNemoErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip(NEMO_ERROR_COLOR);
        return db;
    }
}
