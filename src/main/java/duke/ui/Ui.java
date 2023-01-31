package duke.ui;

import duke.task.Task;
import duke.task.TaskList;


/**
 * Class for handling UI (user facing interface)
 */
public class Ui {
    private final String UI_LOGO = "             _____             _____   _    _              _   _ \n"
            + "     /\\     |_   _|           / ____| | |  | |     /\\     | \\ | |\n"
            + "    /  \\      | |    ______  | |      | |__| |    /  \\    |  \\| |\n"
            + "   / /\\ \\     | |   |______| | |      |  __  |   / /\\ \\   | . ` |\n"
            + "  / ____ \\   _| |_           | |____  | |  | |  / ____ \\  | |\\  |\n"
            + " /_/    \\_\\ |_____|           \\_____| |_|  |_| /_/    \\_\\ |_| \\_|\n"
            + "                                                                 \n"
            + "                                                                 ";

    private final String GUI_LOGO =
                      "              _____             _____   _    _              _   _       \n"
                    + "     /\\       |_   _|           / ____| | |  | |     /\\      | \\ | |        \n"
                    + "    /  \\        | |    ______  | |      | |__| |    /  \\     |  \\| |        \n"
                    + "   / /\\ \\      | |   |______| | |       |  __  |   / /\\ \\    | . ` |       \n"
                    + "  / ____ \\   _| |_           | |____  | |  | |  / ____ \\   | |\\  |          \n"
                    + " /_/    \\_\\ |_____|         \\_____| |_|  |_| /_/    \\_\\ |_| \\_|          \n"
                    + "                                                                               \n"
                    + "                                                                                 ";
    private final String LINE_BREAK = "\n\t ^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^\n";
    /**
     * Default constructor.
     */
    public Ui() {
    }

    /**
     * Method for first greet message.
     */
    public void greet() {
        System.out.println("\t Hello I'm\n" + UI_LOGO);
        System.out.println("\t What can I do for you?" + LINE_BREAK);
    }

    /**
     * Gives greet message for GUI.
     *
     * @return String message for GUI.
     */
    public String guiGreet() {
        return "\t Hello I'm\n" + GUI_LOGO + "\n\t What can I do for you?\n";
    }

    /**
     * Method for ending message.
     */
    public void end() {
        System.out.println(LINE_BREAK
                + "\t Bye. See you next time! :)\n"
                + LINE_BREAK);
    }

    /**
     * Method for message when adding task to taskList.
     *
     * @param task task to be added to list.
     * @param size size of list with new task.
     */
    public void addTask(Task task, int size) {
        System.out.println(LINE_BREAK + "\t Adding the task:\n\t\t" + task
                + "\n\t You now have " + size + " task(s)." + LINE_BREAK);
    }

    /**
     * Method for message when adding task to taskList for GUI.
     *
     * @param task task to be added to list.
     * @param size size of list with new task.
     * @return String message for GUI.
     */
    public String guiAddTask(Task task, int size) {
        return "\t Adding the task:\n\t\t" + task
                + "\n\t You now have " + size + " task(s).\n";
    }

    /**
     * Method for message when deleting task.
     *
     * @param item item to be deleted.
     * @param size size of list with the task included.
     */
    public void deleteMessage(Task item, int size) {
        System.out.println(LINE_BREAK + "\t 1 less task! :)");
        System.out.println("\t\t" + item + "\n\tNow you have " + (size - 1) + " tasks left!" + LINE_BREAK);
    }

    /**
     * Method for message when deleting task for GUI.
     *
     * @param item item to be deleted.
     * @param size size of list with the task included.
     * @return String message for GUI.
     */
    public String guiDeleteMessage(Task item, int size) {
        return "\t 1 less task! :)\n"
                + "\t\t" + item + "\n\tNow you have " + (size - 1) + " tasks left!\n";
    }

    /**
     * Method for message when marking task as done.
     *
     * @param item item to be marked as done.
     */
    public void markMessage(Task item) {
        System.out.println(LINE_BREAK + "\t Great job completing your task! :)");
        System.out.println("\t\t" + item + LINE_BREAK);
    }

    /**
     * Return mark message for GUI.
     * @param item
     * @return
     */
    public String guiMarkMessage(Task item) {
        return "\t Great job completing your task! :)\n\t\t" + item;
    }

    /**
     * Method for message when marking task as undone.
     *
     * @param item to be unmarked.
     */
    public void unmarkMessage(Task item) {
        System.out.println(LINE_BREAK + "\t I see you want to redo this task...");
        System.out.println("\t\t" + item + LINE_BREAK);
    }

    /**
     * Return mark message for GUI.
     * @param item
     * @return
     */
    public String guiUnmarkMessage(Task item) {
        return "\t I see you want to redo this task...\n\t\t" + item;
    }

    /**
     * Method for message when user asks for whole list.
     *
     * @param taskList list to be printed.
     */
    public void print(TaskList taskList) {
        System.out.println(LINE_BREAK + "\tHere are all your tasks, good luck!");
        System.out.print(taskList.toString());
        System.out.println(LINE_BREAK);
    }

    /**
     * Returns tasklist in string representation for GUI.
     *
     * @param taskList tasklist to print.
     * @return String representation of tasklist.
     */
    public String guiPrint(TaskList taskList) {
        return "\tHere are all your tasks, good luck!\n" + taskList.toString();
    }

    /**
     * Method to show user a failed search.
     */
    public void failedSearch() {
        System.out.println(LINE_BREAK + "\tThere are no matching tasks in your list :(\n"
                + LINE_BREAK);
    }

    /**
     * Method to show user a failed search for GUI.
     *
     * @return String message for GUI.
     */
    public String guiFailedSearch() {
        return "\tThere are no matching tasks in your list :(\n";
    }

    /**
     * Method to print results of a word search.
     * @param temp taskList to be printed.
     */
    public void printSearch(TaskList temp) {
        System.out.println(LINE_BREAK + "\tHere are the matching tasks!");
        System.out.print(temp.toString());
        System.out.println(LINE_BREAK);
    }

    /**
     * Method to print results of a word search for GUI.
     *
     * @param temp taskList to be printed.
     * @return String message for GUI.
     */
    public String guiPrintSearch(TaskList temp) {
        return "\tHere are the matching tasks!\n"
                + temp.toString() + "\n";
    }

    /**
     * Method for loading error on startup.
     */
    public void showLoadingError() {
        System.out.println(LINE_BREAK + "\tLoading error :(");
        System.out.println(LINE_BREAK);
    }

}
