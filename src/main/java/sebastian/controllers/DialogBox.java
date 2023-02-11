package sebastian.controllers;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;


/**
 * Class representing a dialog box
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor
     * @param text dialog text
     * @param img avatar
     */
    public DialogBox(String text, Image img) {
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
        this.setStyle();
    }

    /**
     * Styles the dialog box and other components it contains
     */
    private void setStyle() {
        Circle circle = new Circle();
        circle.setCenterY(25.0);
        circle.setCenterX(25.0);
        circle.setRadius(25.0);
        displayPicture.setClip(circle);

        this.setSpacing(10);
        this.setFillHeight(false);

        BorderStroke borderStroke = new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID,
                new CornerRadii(20.0), new BorderWidths(1.0));
        Border dialogBorder = new Border(borderStroke);
        dialog.setBorder(dialogBorder);
        dialog.setPadding(new Insets(10, 10, 10, 10));
    }

    /**
     * Flips a dialog box along the vertical axis
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Label l = (Label) tmp.get(0);
        l.setAlignment(Pos.TOP_LEFT);
        l.setTextAlignment(TextAlignment.LEFT);

        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Instantiates a dialog box using the information provided
     * @param text the content to be shown in the dialog box
     * @param img the user avatar
     * @return the dialog instance
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Triggers a response of Sebastian
     * @param text user input
     * @param img user avatar
     * @return dialog box containing the response and avatar of sebastian
     */
    public static DialogBox getSebastianDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
