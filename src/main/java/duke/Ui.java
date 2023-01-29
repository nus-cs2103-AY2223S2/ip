package duke;

public class Ui {
    private final static String INDENT_LINE = "____________________________________________________________";
    private TaskList taskList;

    /**
     * Parameterized constructor to create the Ui object
     * @param taskList the TaskList containing the list of tasks
     */
    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Prints a given String in a format characteristic of the Ui
     * @param toDisplay the String to be displayed in the formatted manner
     */
    private void printFormatted(String toDisplay) {
        System.out.println(INDENT_LINE + "\n" + toDisplay + "\n" + INDENT_LINE);
    }

    /**
     * Returns the String representation of the list of tasks
     * @return the String representation of the list of tasks
     */
    private String getTaskList() {
        String taskListString = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size() - 1; i++) {
            taskListString += (i + 1) + "." + taskList.get(i) + "\n";
        }
        taskListString += (taskList.size() + "." + taskList.get(taskList.size() - 1));
        return taskListString;
    }

    /**
     * Prints the list of tasks
     */
    public void showTaskList() {
        printFormatted(getTaskList());
    }

    /**
     * Prints the initial message characteristic of the Ui
     */
    public void showInitMessage() {
        printFormatted("Hello! I'm Vincent\n" + "What can I do for you?");
    }

    /**
     * Prints a message on encountering the "bye" command
     */
    public void showByeMessage() {
        printFormatted("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a messagew on Task deletion
     * @param deletedTask the deleted Task
     */
    public void showDeleteMessage(Task deletedTask) {
        printFormatted("Noted. I've removed this task:\n" + deletedTask + "\nNow you have " +
                taskList.size() + " tasks in the list.");
    }

    /**
     * Prints a message on making a Task as done
     * @param index the index of the Task marked as done
     */
    public void showMarkMessage(int index) {
        printFormatted("Nice! I've marked this task as done:\n" +
                (index + 1) + "." + taskList.get(index));
    }

    /**
     * Prints a message on making a Task as undone
     * @param index the index of the Task marked as undone
     */
    public void showUnmarkMessage(int index) {
        printFormatted("OK, I've marked this task as not done yet:\n" +
                (index + 1) + "." + taskList.get(index));
    }

    /**
     * Prints a message on adding a Task to the list
     */
    public void showAddMessage() {
        int len = taskList.size();
        printFormatted("Got it. I've added this task:\n" + taskList.get(len - 1) +
                "\nNow you have " + len + " tasks in the list.");
    }

    /**
     * Prints a message if the file could not be loaded
     */
    public void showLoadingError() {
        printFormatted("The file could not be loaded");
    }
}
