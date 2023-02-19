package duke;

public class Ui {
    private final static String INDENT_LINE = "____________________________________________________________";

    /**
     * Prints a given String in a format characteristic of the Ui
     * @param toDisplay the String to be displayed in the formatted manner
     */
    private String printFormatted(String toDisplay) {
        return toDisplay;
//        return INDENT_LINE + "\n" + toDisplay + "\n" + INDENT_LINE;
    }

    /**
     * Returns the String representation of the list of tasks
     * @param taskList the list of tasks
     * @return the String representation of the list of tasks
     */
    private String getTaskList(TaskList taskList) {
        String taskListString = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size() - 1; i++) {
            taskListString += (i + 1) + "." + taskList.get(i) + "\n";
        }
        taskListString += (taskList.size() + "." + taskList.get(taskList.size() - 1));
        return taskListString;
    }

    /**
     * Prints the list of tasks
     * @param taskList the list of tasks
     */
    public String showTaskList(TaskList taskList) {
        if (!taskList.isEmpty()) {
            return printFormatted(getTaskList(taskList));
        } else {
            return "There are no tasks in your list";
        }
    }

    /**
     * Prints the initial message characteristic of the Ui
     */
    public String showInitMessage() {
        return printFormatted("Hello! I'm Vincent\n" + "What can I do for you?");
    }

    /**
     * Prints a message on encountering the "bye" command
     */
    public String showByeMessage() {
        return printFormatted("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a message on Task deletion
     * @param deletedTask the deleted Task
     * @param len the length of the list of tasks after deletion
     */
    public String showDeleteMessage(Task deletedTask, int len) {
        return printFormatted("Noted. I've removed this task:\n" + deletedTask + "\nNow you have " +
                len + " tasks in the list.");
    }

    /**
     * Prints a message on marking a Task as done
     * @param task the Task marked as done
     * @param index the index of the Task marked as done
     */
    public String showMarkMessage(Task task, int index) {
        return printFormatted("Nice! I've marked this task as done:\n" +
                (index + 1) + "." + task);
    }

    /**
     * Prints a message on marking a Task as undone
     * @param task the Task marked as undone
     * @param index the index of the Task marked as undone
     */
    public String showUnmarkMessage(Task task, int index) {
        return printFormatted("OK, I've marked this task as not done yet:\n" +
                (index + 1) + "." + task);
    }

    /**
     * Prints a message on adding a Task to the list
     * @param task the Task added to the list
     * @param len the length of the list of tasks after addition
     */
    public String showAddMessage(Task task, int len) {
        return printFormatted("Got it. I've added this task:\n" + task +
                "\nNow you have " + len + " tasks in the list.");
    }

    /**
     * Prints a message if the file could not be loaded
     */
    public String showLoadingError() {
        return printFormatted("The file could not be loaded");
    }
}
