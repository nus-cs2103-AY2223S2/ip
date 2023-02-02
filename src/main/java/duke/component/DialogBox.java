package duke.component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayedIcon;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayedIcon = iv;
        text.setWrapText(true);
        displayedIcon.setFitWidth(100.0);
        displayedIcon.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayedIcon);
    }

    public void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        FXCollections.reverse(this.getChildren());
    }

}
