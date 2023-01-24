package interfaces;

import model.Task;

import java.util.List;

public interface View {
    void showMessage(String string);
    String getUserInput();

    void renderTasks(List<Task> tasks);
}
