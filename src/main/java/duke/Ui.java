package duke;

import java.util.List;

public class Ui {
    // Length of horizontal line
    private static final int HORIZONTAL_LINE_LENGTH = 80;

    /** Print methods */

    /**
     * Prints a horizontal line as a divider.
     */
    public void printHorizontalLine() {
        for (int i = 0; i < HORIZONTAL_LINE_LENGTH; i++) {
            System.out.print("\u2500");
        }

        System.out.println();
    }

    /**
     * Prints the greeting message.
     */
    public void showGreeting() {
        printHorizontalLine();
        System.out.println(
            "Karen:\n"
            + "Can I speak to your manager?\n"
            + "Just kidding, this is Karen. How can I help you today?"
        );
        printHorizontalLine();
    }

    /**
     * Prints the exit message.
     */
    public void showExit() {
        printHorizontalLine();
        System.out.println("Karen:\n" + "Bye. This was of great inconvenience.");
        printHorizontalLine();
    }

    /**
     * Prints Karen, the name of the chatbot.
     */
    public void showKaren() {
        printHorizontalLine();
        System.out.println("Karen:");
    }

    /**
     * Prints the tasks in the task list.
     *
     * @param taskList Current list of tasks.
     */
    public void showTaskList(List<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            System.out.println((i + 1) + ". " + currTask.toString());
        }
    }

    /**
     * Prints message when a task is marked as done.
     *
     * @param task Task marked as done.
     */
    public void showTaskMarkDone(Task task) {
        System.out.println(
            "Congrats, I guess you get a medal?\n"
            + task
        );
    }

    /**
     * Prints message when a task is marked as not done.
     *
     * @param task Task marked as not done.
     */
    public void showTaskMarkUndone(Task task) {
        System.out.println(
            "Why are you so lazy?\n"
            + task
        );
    }

    /**
     * Prints message when a task is deleted from the task list.
     *
     * @param task Task that was deleted.
     * @param taskList Current list of tasks.
     */
    public void showDeleteTask(Task task, List<Task> taskList) {
        System.out.println(
            "Okay okay, this has been removed:\n"
            + task.toString()
            + "\nNow you have " + taskList.size() + " tasks left."
        );
    }

    /**
     * Prints message when a new task is added to the task list.
     *
     * @param task Task that was added.
     * @param taskList Current list of tasks.
     */
    public void showAddTask(Task task, List<Task> taskList) {
        System.out.println(
            "You better finish this soon:\n"
            + task
            + "\nCan you finish all " + taskList.size() + " tasks in your list?"
        );
    }

    /** Error methods */
    /**
     * Prints error message.
     *
     * @param message Error message to be printed.
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
