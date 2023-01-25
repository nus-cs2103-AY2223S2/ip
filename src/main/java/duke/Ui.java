package duke;

/**
 * User interface class to interact with the user.
 */
public class Ui {
    static final String BORDER = "----------------------------------------";

    /**
     * Prints welcome message.
     */
    protected void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "What can I do for you?\n" + BORDER);
    }

    /**
     * Prints border.
     */
    protected void showLine() {
        System.out.println(BORDER);
    }

    /**
     * Prints error message.
     *
     * @param e DukeException.
     */
    protected void showError(DukeException e) {
        System.out.println(e.getMessage() + "\n" + BORDER);
    }

    /**
     * Prints exit message.
     */
    protected void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!\n" + BORDER);
    }

    /**
     * Prints mark done message of task.
     *
     * @param t Task marked done.
     */
    protected void markDoneMessage(Task t) {
        System.out.println("Nice! I've marked this task as done:\n"
                + t.fullMessage() + "\n" + BORDER);
    }

    /**
     * Prints mark undone message of task.
     *
     * @param t Task marked undone.
     */
    protected void markUndoneMessage(Task t) {
        System.out.println("OK, I've marked this task as not done yet:\n"
                + t.fullMessage() + "\n" + BORDER);
    }

    /**
     * Prints number of elements in the task.
     *
     * @param taskList Task manager.
     */
    protected void sizeMessage(TaskList taskList) {
        System.out.println("Now you have " + taskList.getSize() + " tasks in this list\n" + BORDER);
    }

    /**
     * Prints add message of Task.
     *
     * @param t Task added.
     * @param taskList Task manager.
     */
    protected void addMessage(Task t, TaskList taskList) {
        System.out.println("Got it. I've added this task:\n" + t.fullMessage());
        sizeMessage(taskList);
    }

    /**
     * Prints delete message of Task.
     *
     * @param t Task deleted.
     * @param taskList Task manager.
     */
    protected void deleteMessage(Task t, TaskList taskList) {
        System.out.println("Noted. I've removed this task:\n" + t.fullMessage());
        sizeMessage(taskList);
    }
}
