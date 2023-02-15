package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.shape.Circle;
/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    private double circleRadius = 40;
    private double centreX = 50;
    private double centreY = 50;

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
        Circle circle = new Circle(centreX, centreY, circleRadius);
        displayPicture.setClip(circle);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    //@@author {https://github.com/radiankrisno/ip}--reused
    //from past iP to get nicer dialog boxes
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox box = new DialogBox(text, img);
        DropShadow shadow = new DropShadow();
        box.setPadding(new Insets(10, 0, 0, 0));
        shadow.setOffsetX(-3);
        shadow.setOffsetY(3);
        box.setEffect(shadow);
        return box;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox box = new DialogBox(text, img);
        DropShadow shadow = new DropShadow();
        box.setPadding(new Insets(5, 0, 0, 10));
        shadow.setOffsetX(3);
        shadow.setOffsetY(3);
        box.setEffect(shadow);
        box.flip();
        return box;
    }
    //@@author
}
