package interfaces;

import model.Task;

import java.util.List;

public interface View {
    void showMessage(String string);
    String getUserInput();
    void showError(String string);
    void renderTasks(List<Task> tasks);
}
