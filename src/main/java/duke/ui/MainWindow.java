package duke.ui;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import duke.Duke;
import duke.main.Storage;
import duke.task.Task;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;

public class MainWindow extends ControllerBase {
    @FXML
    private MenuBar mainMenuBar;

    @FXML
    private ListView<Task> taskListView;

    @Override
    public void setDuke(Duke duke) {
        super.setDuke(duke);
        taskListView.setCellFactory((task) -> {
            return new ListCell<>();
        });
    }

    private void setTasks(List<Task> tasks) {
        taskListView.setItems(FXCollections.observableList(tasks));
    }

    @FXML
    private void quit() {
        // Run cleanup tasks
        System.exit(0);
    }

    @FXML
    private void onOpenTaskWindow() throws IOException {
        Dialog<Optional<Task>> diag = AddTaskWindow.getAddTaskDialog();
        diag.show();
        diag.getResult().ifPresent((task) -> getDuke().getTaskList().add(task));
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
