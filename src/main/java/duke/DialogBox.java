package duke;

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
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
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
    private Circle clippedImage;

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
        clippedImage.setFill(new ImagePattern(img));

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

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Node;
//import javafx.scene.control.Label;
//import javafx.scene.effect.DropShadow;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.StackPane;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.ImagePattern;
//import javafx.scene.shape.Circle;
//
//public class DialogBox extends HBox {
//    private Label text;
//    private ImageView displayPicture;
//
//    public DialogBox(Label l, ImageView iv) {
//        text = l;
//        displayPicture = iv;
//
//        text.setWrapText(true);
//        displayPicture.setFitWidth(100.0);
//        displayPicture.setFitHeight(100.0);
//
//        Circle makeCircle = new Circle(50, 50, 50);
//        iv.setClip(makeCircle);
//        iv.setEffect(new DropShadow(100, Color.BLACK));
//
//        Circle border = new Circle(50, 50, 51);
//        border.setFill(Color.RED);
//
//        StackPane stackPane = new StackPane();
//        stackPane.getChildren().addAll(border, iv);
//
//        text.setPadding(new Insets(10, 10, 50, 10)); //creating padding between image and text
//
//        this.setAlignment(Pos.TOP_RIGHT);
//        this.getChildren().addAll(text, stackPane);
//    }
//    private void flip() {
//        this.setAlignment(Pos.TOP_LEFT);
//        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
//        FXCollections.reverse(tmp);
//        this.getChildren().setAll(tmp);
//    }
//
//    public static DialogBox getUserDialog(Label l, ImageView iv) {
////        l.setPadding(new Insets(10, 10, 50, 10));
//        return new DialogBox(l, iv);
//    }
//
//    public static DialogBox getDukeDialog(Label l, ImageView iv) {
////        l.setPadding(new Insets(10, 10, 50, 10));
//        var db = new DialogBox(l, iv);
//        db.flip();
//        return db;
//    }
//}
