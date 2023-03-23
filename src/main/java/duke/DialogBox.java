package duke;

import java.io.IOException;

import duke.ui.MainWindow;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * HBox class representing the individual dialog boxes.
 */
public class DialogBox extends HBox {
    private static final Color DUKE_BG_COLOR = Color.rgb(54, 54, 54);
    private static final Color USER_BG_COLOR = Color.rgb(2, 119, 189);
    private static final CornerRadii CORNER_RADIUS = new CornerRadii(5);
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;


    /**
     * Creates a new DialogBox.
     * @param l Label object to be shown
     * @param iv ImageView object to be shown
     */
    private DialogBox(String l, Image iv, Color labelColor) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Styling dialog
        dialog.setText(l);
        dialog.setBackground(new Background(
               new BackgroundFill(
                       labelColor,
                       CORNER_RADIUS,
                       Insets.EMPTY
               )
        ));
        // Styling image
        double radius = displayPicture.getFitWidth() / 2;
        Circle circleClip = new Circle(radius, radius, radius);
        displayPicture.setClip(circleClip);
        displayPicture.setImage(iv);
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
    public static DialogBox getUserDialog(String l, Image iv) {
        return new DialogBox(l, iv, USER_BG_COLOR);
    }

    /**
     * Returns a DialogBox for the duke text.
     * @param l Label of duke.
     * @param iv ImageView of duke.
     * @return DialogBox for duke to be served in the GUI.
     */
    public static DialogBox getDukeDialog(String l, Image iv) {
        var db = new DialogBox(l, iv, DUKE_BG_COLOR);
        db.flip();
        return db;
    }
}
