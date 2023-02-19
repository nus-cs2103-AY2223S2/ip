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

/**
 * A main window for the Miki JavaFX GUI.
 */
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

    /**
     * Sets the <code>Miki</code> core used to respond to user input.
     *
     * @param miki <code>Miki</code> core for responses.
     */
    public void setMiki(Miki miki) {
        this.miki = miki;
    }

    @FXML
    private void handleUserInput() {
        miki.respond(userInput.getText());
    }

    /**
     * Binds this <code>MainWindow</code> to a supplied <code>Stage</code> for node resizing.
     *
     * @param stage <code>Stage</code> to bind to.
     */
    public void bindStage(Stage stage) {
        bgImageView.setPreserveRatio(true);
        bgImageView.fitWidthProperty().bind(stage.widthProperty());
        bgImageView.fitHeightProperty().bind(stage.heightProperty());
    }

    /**
     * Adds a new <code>DialogBox</code> to the dialog panel.
     *
     * @param text text contents of the <code>DialogBox</code>.
     * @param pov quoted speaker of the <code>DialogBox</code>.
     */
    public void addDialog(String text, DialogBox.Pov pov) {
        dialogContainer.getChildren().add(new DialogBox(text, pov));
        assert !dialogContainer.getChildren().isEmpty() : "Dialog Container should be non-empty after adding element";
    }

    /**
     * Adds a new <code>TaskBox</code> to the task-list panel.
     *
     * @param text text contents of the <code>TaskBox</code>.
     */
    public void addTask(String text) {
        taskContainer.getChildren().add(new TaskBox(text));
        assert !taskContainer.getChildren().isEmpty() : "Task Container should be non-empty after adding element";
    }

    /**
     * Clears the user input <code>TextField</code>.
     */
    public void clearInput() {
        userInput.clear();
        assert userInput.getText().isEmpty() : "User Input field should be blank after clearing";
    }

    /**
     * Clears the task-list panel.
     */
    public void clearTasks() {
        taskContainer.getChildren().clear();
        assert taskContainer.getChildren().size() == 0 : "Task Container should be empty after clearing";
    }
}
