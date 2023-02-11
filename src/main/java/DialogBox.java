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
import javafx.scene.text.Text;


/**
 * Controller for Dialog Box, provides visual information of the user's dialog.
 * 
 * @author Brian Quek
 */
public class DialogBox extends HBox {

    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;


    /**
     * Constructor for DialogBox.
     * @param text the message of the dialog box.
     * @param img the display image of the dialog box.
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
    }

    /**
     * Switches the alignment of the dialog box to align left. (Default: align right)
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    
    /** 
     * Returns a User Dialog Box (Aligned right).
     * @param text User's message
     * @param img User's display image
     * @return DialogBox containing Text and ImageView of the User's information
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }
    
    /** 
     * Returns a Bot Dialog Box (Aligned right).
     * @param text Bot's message
     * @param img Bot's display image
     * @return DialogBox containing Text and ImageView of the Bot's information
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
