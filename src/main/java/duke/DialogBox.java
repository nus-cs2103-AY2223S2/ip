package duke;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;
    private static Color DUKE_BG_COLOR = Color.rgb(54, 54, 54);
    private static Color USER_BG_COLOR = Color.rgb(2, 119, 189);
    private static CornerRadii CORNER_RADIUS = new CornerRadii(5);
    private static double IMAGE_WIDTH = 40.0;
    private static double IMAGE_HEIGHT = 40.0;

    /**
     * Creates a new DialogBox.
     * @param l Label object to be shown
     * @param iv ImageView object to be shown
     */
    private DialogBox(Label l, ImageView iv, Color labelColor) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(IMAGE_WIDTH);
        displayPicture.setFitHeight(IMAGE_HEIGHT);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);

        // Adding padding to the dialog box
        this.setPadding(new Insets(10, 10, 0, 10));
        this.setSpacing(5);

        // Styling the Label
        text.setPadding(new Insets(5, 10, 5, 10));
        text.setBackground(new Background(
                new BackgroundFill(
                        labelColor,
                        CORNER_RADIUS,
                        Insets.EMPTY
                )
        ));
        text.setTextFill(Color.WHITE);

        // Clipping the image to be a circle
        double radius = displayPicture.getFitWidth() / 2;
        Circle circleClip = new Circle(radius, radius, radius);
        displayPicture.setClip(circleClip);
    }

    /**
     * Flips the display horizontally. Helper function.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns a DialogBox for the user text.
     * @param l Label of the user.
     * @param iv ImageView of the user.
     * @return DialogBox for the user to be served in the GUI.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv, USER_BG_COLOR);
    }

    /**
     * Returns a DialogBox for the duke text.
     * @param l Label of duke.
     * @param iv ImageView of duke.
     * @return DialogBox for duke to be served in the GUI.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv, DUKE_BG_COLOR);
        db.flip();
        return db;
    }
}
