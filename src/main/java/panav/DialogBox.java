package panav;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        text.setBackground(new Background(
                new BackgroundFill(Color.MINTCREAM, CornerRadii.EMPTY, new Insets(3))));
        displayPicture = iv;
        


        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    public DialogBox(Label l, ImageView iv, boolean leftSide) {
        text = l;
        text.setBackground(new Background(
                new BackgroundFill(Color.MINTCREAM, CornerRadii.EMPTY, new Insets(3))));
        displayPicture = iv;


        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        if (leftSide) {
            this.setAlignment(Pos.TOP_LEFT);
        } else {
            this.setAlignment(Pos.TOP_RIGHT);
        }

        this.getChildren().addAll(displayPicture, text);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);

        return db;

    }

    public static DialogBox getPanavDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

}
