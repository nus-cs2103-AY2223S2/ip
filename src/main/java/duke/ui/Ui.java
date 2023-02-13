package duke.ui;

import java.util.ArrayList;

import duke.data.TaskList;
import duke.data.task.Task;


/**
 * Ui of application.
 */
public class Ui {

    /**
     * Display to user task count.
     *
     * @param size Number of tasks.
     * @return Show task count string.
     */
    public String showTaskCount(int size) {
        return "There are now " + Integer.toString(size) + " task(s) in the list.";
    }

    /**
     * Displays to user the task added.
     *
     * @param task Task to be added.
     * @return Add task string.
     */
    public String showAddTask(Task task) {
        return "Understood. I have added the task:\n" + task.toString();
    }

    /**
     * Displays to user the task deleted.
     *
     * @param task Task to be deleted.
     * @return Delete task string.
     */
    public String showDeleteTask(Task task) {
        return "Noted. I have removed the task:\n" + task.toString();
    }

    /**
     * Displays all tasks in TaskList.
     *
     * @param tasks TaskList containing tasks to be displayed.
     * @return All tasks string.
     */
    public String showAllTasks(TaskList tasks) {

        if (tasks.isEmpty()) {
            return "No tasks added yet.";
        } else {
            String str = "";
            for (int i = 0; i < tasks.size(); i++) {
                str += (i + 1) + ". " + tasks.get(i).toString() + "\n";
            }
            return str;
        }
    }

    /**
     * Displays tasks that contains a string.
     *
     * @param taskArray Array containing the tasks that contain the string.
     * @return Found tasks string.
     */
    public String showMatchingTasks(ArrayList<Task> taskArray) {
        if (taskArray.size() == 0) {
            return "No tasks found.";
        } else {
            String str = "These are the tasks I have found:\n";
            for (int i = 0; i < taskArray.size(); i++) {
                str += (i + 1) + ". " + taskArray.get(i).toString() + "\n";
            }
            return str;
        }
    }

    /**
     * Displays task marked.
     *
     * @param task Task to be marked.
     * @return Mark string.
     */
    public String showMarked(Task task) {
        return task.outputMarked() + task.toString();
    }

    /**
     * Displays task unmarked.
     *
     * @param task Task to be unmarked.
     * @return Unmark string.
     */
    public String showUnmarked(Task task) {
        return task.outputUnmarked() + task.toString();
    }


    /**
     * Displays goodbye message.
     *
     * @return Goodbye string.
     */
    public String showGoodbye() {
        return "Tasks saved. Thank you for your patronage. Goodbye!";
    }

    /**
     * Displays error message.
     *
     * @param error Error to be displayed.
     */
    public void showError(String error) {
        System.out.println("Error: " + error);
    }

}
