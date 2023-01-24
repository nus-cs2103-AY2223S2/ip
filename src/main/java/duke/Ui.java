package duke;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Medium which the chatbot use to interact with the user.
 *
 * @author wz2k
 */
public class Ui {
    private void displayText(String text) {
        System.out.println(text);
    }

    /**
     * Prints the task details.
     *
     * @param task Task to be printed.
     */
    public void replyTaskInfo(Task task) {
        displayText("  " + task.toString());
    }

    /**
     * Prints the end of chat message.
     */
    public void endChat() {
        displayText("Chat with duke.Duke has ended");
    }

    /**
     * Prints the start of chat message.
     */
    public void greetUser() {
        displayText("Hello. This is duke.Duke");
    }

    /**
     * Prints the total number of tasks.
     */
    public void replyTotalTasks(TaskList taskList) {
        displayText("Total tasks: " + taskList.getSize());
    }

    /**
     * Prints the task added message.
     */
    public void replyTaskAdded(Task task) {
        displayText("The following task has been added:");
        displayText("  " + task.toString());
    }

    /**
     * Prints the task marked message.
     */
    public void replyTaskMarked(Task task) {
        displayText("The following task is marked as done:");
        displayText("  " + task.toString());
    }

    /**
     * Prints the task unmarked message.
     */
    public void replyTaskUnmarked(Task task) {
        displayText("The following task is marked as not done:");
        displayText("  " + task.toString());
    }

    /**
     * Prints the task deleted message.
     */
    public void replyTaskDeleted(Task task) {
        displayText("The following task has been deleted:");
        displayText("  " + task.toString());
    }

    /**
     * Prints the no task stored message.
     */
    public void replyEmptyTaskList() {
        displayText("No task stored.");
    }

    /**
     * Prints the error message.
     */
    public void replyError(String errorMessage) {
        displayText("An error has occurred!");
        displayText(errorMessage);
    }

    public void replySearchStart() {
        this.displayText("The following tasks matches your query:");
    }
}
