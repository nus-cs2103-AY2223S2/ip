package shigure.fxcontrol;

import core.Miki;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends AnchorPane {
    @FXML
    private ImageView bgImageView;
    @FXML
    private ScrollPane dialogScrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private ScrollPane taskScrollPane;
    @FXML
    private VBox taskContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Miki miki;

    @FXML
    public void initialize() {
        dialogScrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setMiki(Miki miki) {
        this.miki = miki;
    }

    @FXML
    private void handleUserInput() {
        miki.respond(userInput.getText());
    }

    public void bindStage(Stage stage) {
        bgImageView.setPreserveRatio(true);
        bgImageView.fitWidthProperty().bind(stage.widthProperty());
        bgImageView.fitHeightProperty().bind(stage.heightProperty());
    }

    public void addDialog(String text, DialogBox.Pov pov) {
        dialogContainer.getChildren().add(new DialogBox(text, pov));
    }

    public void addTask(String text) {
        taskContainer.getChildren().add(new TaskBox(text));
    }

    public void clearInput() {
        userInput.clear();
    }

    public void clearTasks() {
        taskContainer.getChildren().clear();
    }
}
