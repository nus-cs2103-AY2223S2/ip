package duke.ui;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;

import duke.App;
import duke.Duke;
import duke.main.Storage;
import duke.task.Task;

public class MainWindow extends ControllerBase {
    @FXML
    private MenuBar mainMenuBar;

    @FXML
    private ListView<Task> taskListView;

    @Override
    public void setDuke(Duke duke) {
        super.setDuke(duke);
        taskListView.setCellFactory((task) -> {
            return new ListCell<>() {
                @Override
                public void updateItem(Task t, boolean empty) {
                    super.updateItem(t, empty);
                    if (empty || t == null) {
                        setText(null);
                    } else {
                        setText(t.toString());
                    }
                }
            };
        });
    }

    private void setTasks(List<Task> tasks) {
        taskListView.setItems(FXCollections.observableList(tasks));
    }

    @FXML
    private void quit() {
        // Run cleanup tasks
        App.sendCloseRequest();
    }

    @FXML
    private void onOpenTaskWindow() throws IOException {
        Dialog<Optional<Task>> diag = AddTaskWindow.getAddTaskDialog();
        diag.showAndWait().ifPresent((taskOptional) -> {
            taskOptional.ifPresent((task) ->  { 
                getDuke().getTaskList().add(task);
                taskListView.itemsProperty().get().add(task);
            });
            // Task optional should not be empty
            assert taskOptional.isPresent();
        });
    }

    @FXML
    private void saveTasksToDisk() throws IOException {
        Storage.saveToDisk("data.dat", getDuke().getTaskList());
    }

    @FXML
    private void loadTasksFromDisk() throws IOException, ClassNotFoundException {
        List<Task> savedTasks = Storage.loadFromDisk("data.dat");
        setTasks(savedTasks);
    }

}
