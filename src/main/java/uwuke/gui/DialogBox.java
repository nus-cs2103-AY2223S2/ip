package uwuke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;
    private static Image userImage;
    private static Image dukeImage;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    public static void setUserImage(Image userImage) {
        assert userImage != null : "User Image should not be null!";
        DialogBox.userImage = userImage;
    }

    public static void setDukeImage(Image dukeImage) {
        assert dukeImage != null : "Duke Image should not be null!";
        DialogBox.dukeImage = dukeImage;
    }
    
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> temp = FXCollections.observableArrayList(this.getChildren()); // why must we use this weird thing?
        FXCollections.reverse(temp);
        this.getChildren().setAll(temp); // Clears original children and add new collection of children
    }

    /**
     * Returns a new DialogBox of the User
     * User Image is kept to the right
     * @param text
     * @param picView
     * @return
     */
    public static DialogBox getUserDialogBox(Label text) {
        return new DialogBox(text, new ImageView(userImage));
    }
    
    /**
     * Returns a new DialogBox of UwUke
     * Uwuke will be on the left
     * 
     * @param text
     * @param picView
     * @return
     */
    public static DialogBox getDukeDialogBox(Label text) {
        DialogBox db = new DialogBox(text, new ImageView(dukeImage));
        db.flip();
        return db;
    }
}
