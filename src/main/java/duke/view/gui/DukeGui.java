package duke.view.gui;
import duke.interfaces.InputEventListener;
import duke.interfaces.View;
import duke.model.Task;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;


public class DukeGui extends Application implements View {
    private InputEventListener inputEventListener;
    private FXMLLoader fxmlLoader;
    private List<Task> displayedTaskList;
    private Stage stage;

    @Override
    public Task getDisplayedTask(int index) {
        return displayedTaskList.get(index);
    }
    @Override
    public int getNumDisplayedTasks() {
        return displayedTaskList.size();
    }
    public DukeGui() {
    }
    public void registerInputEventListener(InputEventListener listener) {
        this.inputEventListener = listener;
    }
    public void showMessage(String message) {
        fxmlLoader.<MainWindow>getController().displayDukeResponse(message);
    }

    @Override
    public void showError(String errorMessage) {
        // TODO
        // current solution is to simply call showMessage on error
        // ideally, change color to red to show error
        showMessage(errorMessage);
    }

    @Override
    public void setTasks(List<Task> tasks, boolean displayTasks) {
        this.displayedTaskList = tasks;
        if (displayTasks) {
            int index = 1;
            StringBuilder tasksString = new StringBuilder();
            tasksString.append("Here are the tasks in your list:\n");
            for (Task task: tasks) {
                tasksString.append(String.format("%d. %s\n", index++, task.toString()));
            }
            showMessage(tasksString.toString());
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.fxmlLoader = new FXMLLoader(DukeGui.class.getResource("/view/MainWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap);
        this.stage = stage;
        this.stage.setScene(scene);
        fxmlLoader.<MainWindow>getController().registerInputEventListener(inputEventListener);
//            fxmlLoader.<MainWindow>getController().setDuke(duke);
        this.stage.show();
    }

    public void stop() {
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished( event -> stage.close());
        delay.play();
    }
}
