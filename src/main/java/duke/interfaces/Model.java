package duke.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import duke.model.Task;

/**
 * Model is an interface for the data model in the task management application.
 * It provides access to the user's task list.
 */
public interface Model {
    /**
     * Gets the list of tasks stored in the model.
     *
     * @return the list of tasks
     */
    List<Task> getTasks();
    Task getTask(int index);
    int getNumberOfTasks();
    Task createTask(String taskDescription);
    Task createTask(String taskDescription, LocalDateTime deadline);
    Task createTask(String taskDescription, LocalDateTime startTime, LocalDateTime endTime);
    void deleteTask(Task task);
    void markTaskDone(Task task);
    void markTaskUndone(Task task);
    List<Task> findTasks(String subStr);
    List<Task> getTasksOn(LocalDateTime time);

}
