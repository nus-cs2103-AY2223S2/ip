package io.gui;

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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 * Dialogue box for user inputs and duke outputs.
 */
public class DialogueBox extends HBox {
    @FXML
    private Label dialogue;
    @FXML
    private ImageView displayPicture;

    /**
     * @param text display text
     * @param img Image of dialogue source.
     */
    private DialogueBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogueBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        dialogue.setText(text);
        HBox.setHgrow(dialogue, Priority.ALWAYS);
        displayPicture.setImage(img);
        double radius = displayPicture.getFitHeight() / 2;
        Circle circleClip = new Circle(
                radius,
                radius,
                radius);
        displayPicture.setClip(circleClip);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_RIGHT);
    }

    public static DialogueBox getUserDialogue(String text, Image img) {
        return new DialogueBox(text, img);
    }

    public static DialogueBox getBotDialogue(String text, Image img) {
        var db = new DialogueBox(text, img);
        db.flip();
        return db;
    }
}
