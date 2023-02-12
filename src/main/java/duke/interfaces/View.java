package duke.interfaces;

import java.util.List;

import duke.model.Task;

public interface View {
    void showMessage(String string);
    String getUserInput();
    void showError(String string);
    void renderTasks(List<Task> tasks);
}
