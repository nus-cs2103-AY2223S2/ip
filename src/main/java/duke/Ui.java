package duke;

public class Ui {
    private static final String WELCOME_MESSAGE = "Hi there, my name's Duke!\n" + "I'm your personal task manager.\n"
        + "How may i help you?\n";
    private static final String LINE = "__________________________________________\n";

    /**
     * constructs a Ui object.
     */
    public Ui() {
    }

    /**
     * creates the welcome message when starting up Duke.
     * @return the String for welcome message.
     */
    public String showWelcome() {
        return (WELCOME_MESSAGE + LINE);
    }

    /**
     * creates the message when a task is added.
     * @param task task to be added.
     * @return the String for adding task.
     */
    public String showAdd(Task task) {
        return "Got it. I've added this task:\n" + task.toString() + "\n";
    }

    /**
     * creates the message when a task is deleted.
     * @param task task to be deleted.
     * @return the String for deleting task.
     */
    public String showDelete(Task task) {
        return "Noted. I've removed this task:\n" + task.toString() + "\n"
                + "your tasklist has been updated:\n";
    }

    /**
     * creates the message that shows the taskList size.
     * @param size size of the task list.
     * @return the String for showing task size.
     */
    public String showTaskSize(int size) {
        return "Now you have " + size + " tasks in the list. \n";
    }

    /**
     * creates the message for when a task is marked as done.
     * @param task task to be marked as done.
     * @return the String for when marking a task as done.
     */
    public String showMarked(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString() + "\n";
    }

    /**
     * creates the message for when a task is marked as undone.
     * @param task task to be marked as undone.
     * @return the String for when marking a task as undone.
     */
    public String showUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:\n"
        + task.toString() + "\n";
    }

    /**
     * creates the message for showing the current task list.
     * @param list the TaskList to be shown.
     * @return the String containing the taskList.
     */
    public String showTaskList(TaskList list) {
        String result = "";
        if (list.isEmpty()) {
            result = "No tasks found.\n";
        } else {
            for (int i = 0; i < list.size(); i++) {
                result += (i + 1) + ":" + list.get(i) + "\n";
            }
        }
        return result;
    }

    /**
     * creates the message for errors.
     * @return the String for errors.
     */
    public String showError() {
        return "Error has occurred.";
    }

    /**
     * creates the message for exiting the program.
     * @return the String for when user closes the program.
     */
    public String showBye() {
        return "Bye! Have a good day!";
    }

    /**
     * creates the message for when there is an IndexOutOfBounds error.
     * @return the String for when the error occurs.
     */
    public String showIndexError() {
        return "No such index, try again!";
    }
}
