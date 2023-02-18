package duke.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class DialogBox extends HBox implements FxmlComponent {

    @FXML
    private Text dialog;

    @FXML
    private ImageView displayPicture;

    public DialogBox(String text, Image img) {
        loadFxml("/view/DialogBox.fxml");
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    public void flip() {
        FXCollections.reverse(getChildren());
        setAlignment(Pos.TOP_LEFT);
    }
}
