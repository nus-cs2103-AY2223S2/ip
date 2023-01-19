import java.util.ArrayList;

/**
 * The skeleton version of Duke.
 * Return appropriate replies or updates user on certain tasks
 * depending on user input.
 */
public class UI {
    /**
     * Welcome introduction.
     * Return the introduction message.
     */
    public static String introduction() {
        return "Good day! My name is Duke. \n" + "How am I of service today?";
    }

    /**
     * Return the departing message.
     */
    public static String departure() {
        return "It has been a great pleasure serving you. \n" + "Have a nice day.";
    }

    /**
     * Return the number of current tasks.
     * @param nameOfTask The number of tasks.
     */
    public String numberOfTasks(Task nameOfTask) {
        return "Now you have " + Task.getNameOfTask().length() + "tasks in the list.";
    }

    /**
     * Return the list of all the current tasks.
     * @param nameOfTask The name of tasks.
     */
    public String printAllTasks(Task nameOfTask) {
        return "Here are the tasks in your list: \n" + nameOfTask;
    }

    /**
     * Returns the message when a task is added.
     * @param added The newly added task.
     */
    public String addTaskMessage(Task added) {
        return "Understood. I have added this task: \n" + added.getStatus();
    }

    /**
     * Return the message when a task is mark as done.
     * @param mark The task to be marked as done.
     */
    public String markDone(Task mark) {
        return "Nice work! This task has been marked as done: \n" + mark.getStatus();
    }

    /**
     * Returns the message when a task is marked as undone.
     * @param unmark The task to be unmarked as done.
     */
    public String markUndone(Task unmark) {
        return "Noted. This task has been marked as not done yet: \n" + unmark.getStatus();
    }

    public String deleteTask(Task deleted) {
        return "Alright. This task has been deleted: \n" + deleted.getStatus();
    }

}
