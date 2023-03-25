package brotherbot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class DialogBox extends HBox {

    public DialogBox(Label l, ImageView iv) {
        formatLabel(l);
        formatImage(iv);
        this.setAlignment(Pos.TOP_RIGHT);
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

    private void formatLabel(Label l) {
        l.setWrapText(true); // enable text wrapping
        l.setMinHeight(Label.USE_PREF_SIZE); // set the minimum height of the label
        setHgrow(l, Priority.ALWAYS);
        l.setTextFill(Color.ORANGE);
        Font poppinsFont = Font.font("Poppins", FontWeight.BOLD, 13.5);
        l.setFont(poppinsFont);
        setMargin(l, new Insets(10, 10, 20, 10));
    }

    private void formatImage(ImageView iv) {
        iv.setFitWidth(100.0);
        iv.setFitHeight(100.0);
        iv.setStyle("-fx-padding: 20; -fx-border-radius: 20");
        iv.setClip(new Circle(50, 50, 50));
    }
}