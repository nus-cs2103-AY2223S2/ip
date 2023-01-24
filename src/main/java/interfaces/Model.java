package interfaces;

import model.Task;

import java.util.List;

public interface Model {
    void addTask(Task task);
    List<Task> getTasks();
}
