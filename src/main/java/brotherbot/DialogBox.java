package brotherbot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class DialogBox extends HBox {

    public DialogBox(Label l, ImageView iv) {

        l.setWrapText(true);
        l.setTextFill(Color.ORANGE);
        l.setStyle("-fx-font-weight: BOLD;");

        Font poppinsFont = Font.font("Poppins", FontWeight.BOLD, 13.5);
        l.setFont(poppinsFont);

        iv.setFitWidth(100.0);
        iv.setFitHeight(110.0);

        this.setAlignment(Pos.TOP_RIGHT);
        setMargin(l, new Insets(0, 10, 0, 10));
        this.getChildren().addAll(l, iv);
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
        return new DialogBox(l, iv);
    }

    public static DialogBox getBrotherDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

}