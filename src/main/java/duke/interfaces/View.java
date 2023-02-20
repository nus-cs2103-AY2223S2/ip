package duke.interfaces;

import java.util.List;

import duke.model.Task;

/**
 * View is an interface for the view component in the task management application.
 * It displays the tasks data and allows users to interact with the task list stored in the model.
 */
public interface View {
    Task getDisplayedTask(int index);
    int getNumDisplayedTasks();
    void showMessage(String string);

    /**
     * Shows an error message to the user.
     *
     * @param errorMessage the error message to show
     */
    void showError(String errorMessage);
    /**
     * Displays the list of tasks.
     *
     * @param tasks the list of tasks to display
     */
    void setTasks(List<Task> tasks, boolean renderToScreen);
}
