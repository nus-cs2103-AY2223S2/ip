package duke.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;

/**
 * Dialog box for either Duke or user
 */
public class DialogBox extends VBox {
    @FXML
    private Label text;
    @FXML
    private Label sender;


    /**
     * Creates a dialog box for either Duke or User
     * @param text text in dialog box
     * @param isDuke whether duke is speaking
     */
    public DialogBox(String text, boolean isDuke) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.text.setText(text);
        this.text.setTextFill(Color.WHITE);
        this.sender.setTextFill(Color.WHITE);
        if (isDuke) {
            this.setAlignment(Pos.TOP_RIGHT);
            this.setStyle("-fx-background-color: rgb(52,53,65)");
            sender.setText("Duke");
        } else {
            this.setStyle("-fx-background-color: rgb(68,70,84);");
            sender.setText("You");
        }
    }
}
