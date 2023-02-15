package duke.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.*;

public class DialogBox extends HBox {
    private static final String DIALOG_BOX_RESOURCE_PATH = "/view/DialogBox.fxml";
    private static final String ROBOTO_BOLD_RESOURCE_PATH = "/fonts/Roboto-Bold.ttf";
    private static final Paint BOT_COLOR = Color.valueOf("D34747");
    private static final Paint USER_COLOR = Color.valueOf("5B78C1");

    @FXML
    private TextFlow dialog;
    @FXML
    private ImageView displayPicture;

    public DialogBox(String text, Image img, Paint paint) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(DIALOG_BOX_RESOURCE_PATH));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Text dialogText = new Text(text);
        dialogText.setFont(Font.loadFont(MainWindow.class.getResourceAsStream(ROBOTO_BOLD_RESOURCE_PATH), 12));
        dialogText.setFill(paint);
        dialog.getChildren().add(dialogText);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        dialog.setTextAlignment(TextAlignment.LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, USER_COLOR);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, BOT_COLOR);
        db.flip();
        return db;
    }
}
