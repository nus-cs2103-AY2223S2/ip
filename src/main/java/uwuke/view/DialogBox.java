package uwuke.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    
    @FXML
    private Label text;

    @FXML
    private ImageView displayPicture;

    private static Image userImage;
    private static Image dukeImage;

    public DialogBox(String inputText, Image inputImage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DialogBox.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            text.setText(inputText);
            displayPicture.setImage(inputImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setUserImage(Image userImage) {
        assert userImage != null : "User Image should not be null!";
        DialogBox.userImage = userImage;
    }

    public static void setDukeImage(Image dukeImage) {
        assert dukeImage != null : "Duke Image should not be null!";
        DialogBox.dukeImage = dukeImage;
    }

    /**
     * Returns a new DialogBox of the User
     * User Image is kept to the right
     * @param text
     * @param picView
     * @return
     */
    public static DialogBox getUserDialogBox(String text) {
        DialogBox db = new DialogBox(text, userImage);
        db.setStyle("-fx-background-color: #343540;");
        return db;
    }
    
    /**
     * Returns a new DialogBox of UwUke
     * Uwuke will be on the left
     * 
     * @param text
     * @param picView
     * @return
     */
    public static DialogBox getDukeDialogBox(String text) {
        DialogBox db = new DialogBox(text, dukeImage);
        return db;
    }
}
