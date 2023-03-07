package sam.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import sam.task.Task;

/**
 * Represents a dialog with an avatar.
 */
public class TaskCard extends HBox {

    private static final String FXML = "/view/TaskCard.fxml";

    @FXML
    private Label id;
    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private Label done;
    @FXML
    private Label type;

    /**
     * Constructs a new TaskCard.
     */
    public TaskCard(Task task, int index) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (index > 0) {
            id.setText(Integer.toString(index));
        }
        title.setText(task.getTitle());
        description.setText(task.getDescription());
        done.setText(task.getStatusIcon());
        type.setText(task.getTypeIcon());
    }
}
