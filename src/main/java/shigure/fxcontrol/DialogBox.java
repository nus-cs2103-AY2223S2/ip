package shigure.fxcontrol;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/bobu_40x40.png"));
    private final Image mikiImage = new Image(this.getClass().getResourceAsStream("/images/fuzuki_40x40.png"));
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    public DialogBox(String text, Pov pov) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            dialog.setText(text);
            displayPicture.setClip(
                    new Circle(displayPicture.getX() + displayPicture.getFitWidth() / 2,
                            displayPicture.getY() + displayPicture.getFitHeight() / 2,
                            20));
            switch (pov) {
            case MIKI:
                displayPicture.setImage(mikiImage);
                flip();
                break;
            case USER:
                displayPicture.setImage(userImage);
                break;
            default:
                assert false : "Invalid DialogBox dialogue perspective";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
    }

    enum Pov {
        MIKI,
        USER
    }
}
