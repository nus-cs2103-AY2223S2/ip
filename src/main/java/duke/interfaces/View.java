package duke.interfaces;

import duke.model.Task;

import java.util.List;

public interface View {
    void showMessage(String string);
    String getUserInput();
    void showError(String string);
    void renderTasks(List<Task> tasks);
}
