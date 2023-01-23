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
        this.displayText("  " + task.toString());
    }

    /**
     * Prints the end of chat message.
     */
    public void endChat() {
        this.displayText("Chat with duke.Duke has ended");
    }

    /**
     * Prints the start of chat message.
     */
    public void greetUser() {
        this.displayText("Hello. This is duke.Duke");
    }

    /**
     * Prints the total number of tasks.
     */
    public void replyTotalTasks(TaskList taskList) {
        this.displayText("Total tasks: " + taskList.getSize());
    }

    /**
     * Prints the task added message.
     */
    public void replyTaskAdded(Task task) {
        this.displayText("The following task has been added:");
        this.displayText("  " + task.toString());
    }

    /**
     * Prints the task marked message.
     */
    public void replyTaskMarked(Task task) {
        this.displayText("The following task is marked as done:");
        this.displayText("  " + task.toString());
    }

    /**
     * Prints the task unmarked message.
     */
    public void replyTaskUnmarked(Task task) {
        this.displayText("The following task is marked as not done:");
        this.displayText("  " + task.toString());
    }

    /**
     * Prints the task deleted message.
     */
    public void replyTaskDeleted(Task task) {
        this.displayText("The following task has been deleted:");
        this.displayText("  " + task.toString());
    }

    /**
     * Prints the no task stored message.
     */
    public void replyEmptyTaskList() {
        this.displayText("No task stored.");
    }

    /**
     * Prints the error message.
     */
    public void replyError(String errorMessage) {
        this.displayText("An error has occurred!");
        this.displayText(errorMessage);
    }
}
