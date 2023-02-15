package duke.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.*;

public class DialogBox extends HBox {
    private static final String DIALOG_BOX_RESOURCE_PATH = "/view/DialogBox.fxml";
    private static final String ROBOTO_BOLD_RESOURCE_PATH = "/fonts/Roboto-Bold.ttf";
    private static final int DIALOGUE_BOX_FONT_SIZE = 12;
    private static final Paint BOT_COLOR = Color.valueOf("D34747");
    private static final Paint USER_COLOR = Color.valueOf("5B78C1");
    private static final String USER_BORDER_RADIUS_STYLE = "-fx-background-radius: 5 5 1 5;";
    private static final String BOT_BORDER_RADIUS_STYLE = "-fx-background-radius: 5 5 5 1;";
    private static final String BACKGROUND_COLOR_STYLE = "-fx-background-color: #D9D9D9;";

    @FXML
    private TextFlow dialog;
    @FXML
    private ImageView displayPicture;

    public DialogBox(String text, Image img, Paint paint, String style) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(DIALOG_BOX_RESOURCE_PATH));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Text dialogText = new Text(text);
        Font dialogueFont = Font.loadFont(
                MainWindow.class.getResourceAsStream(ROBOTO_BOLD_RESOURCE_PATH), DIALOGUE_BOX_FONT_SIZE);
        dialogText.setFont(dialogueFont);
        dialogText.setFill(paint);
        dialog.setStyle(style);
        DropShadow dropShadow = new DropShadow(BlurType.ONE_PASS_BOX, (Color) paint, 0, 0, 0, 5);
        dialog.setEffect(dropShadow);
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
        return new DialogBox(text, img, USER_COLOR,
                String.format("%s\n%s", BACKGROUND_COLOR_STYLE, USER_BORDER_RADIUS_STYLE));
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, BOT_COLOR,
                String.format("%s\n%s", BACKGROUND_COLOR_STYLE, BOT_BORDER_RADIUS_STYLE));
        db.flip();
        return db;
    }
}
