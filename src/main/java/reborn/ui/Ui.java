package reborn.ui;

import java.util.ArrayList;

import reborn.data.TaskList;
import reborn.data.task.Task;


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
        return "Take your " + Integer.toString(size) + " task(s)!";
    }

    /**
     * Displays to user the task added.
     *
     * @param task Task to be added.
     * @return Add task string.
     */
    public String showAddTask(Task task) {
        return "I'll help add this for now:\n" + task.toString();
    }

    /**
     * Displays to user the task deleted.
     *
     * @param task Task to be deleted.
     * @return Delete task string.
     */
    public String showDeleteTask(Task task) {
        return "Less stuff for me to keep note hehe, removed:\n" + task.toString();
    }

    /**
     * Displays all tasks in TaskList.
     *
     * @param tasks TaskList containing tasks to be displayed.
     * @return All tasks string.
     */
    public String showAllTasks(TaskList tasks) {

        if (tasks.isEmpty()) {
            return "No tasks added yet, thank god for that.";
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
            return "No tasks found, what are you even doing....";
        } else {
            String str = "Here you go, now stop wasting my time:\n";
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
        return "Tasks saved. Now get out of here!";
    }

    /**
     * Displays error message.
     *
     * @param error Error to be displayed.
     */
    public void showError(String error) {
        System.out.println("Bruh: " + error);
    }

}
