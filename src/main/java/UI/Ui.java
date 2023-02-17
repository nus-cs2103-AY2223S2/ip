package UI;

import task.Task;
import task.TaskList;

public class Ui {
    // For all interactions with the user

    /**
     * Closes the UI from accepting any more inputs.
     */
    public static String close() {
        //Todo: Figure out a way to close the application when this is called
        return "Pleasure doing business with you.";
    }

    /**
     * Prints the corresponding error message when the user inputs a command not registered
     *  in Duke.
     */
    public static String unknownCommand() {
        return "Woah there, didn't quite get that. Come again?";
    }

    /**
     * Prints the opening message for Duke.
     */
    public static String startMessage() {
        return "My name is Skyler White yo \nHow can I help you? Type help to learn " +
                "about the full list of commands.";
    }

    /**
     * Displays an error message if Duke fails to read from the user's provided input path or the default write path
     * of the program after it terminates.
     */
    public static String showLoadingError() {
        return "Looks like you don't have any old lists for me to include. That's alright; we'll start" +
                " from scratch!";
    }

    public static String showArrayOutOfBoundsError() {
        return "Woah there. Got an index problem. That entry does not exist.";
    }

    /**
     * Prints the error message for when a user makes a command, but with incorrect formatting.
     * @param message a String that contains the details for why the command failed.
     */
    public static String showInvalidInputError(String message) {
        return "Woah. That command doesn't look right. Here's what seems to be wrong:\n" + message;
    }

    /**
     * Prints the success message when the given task in the list has been marked as completed.
     * @param task the task that has been marked as completed
     */
    public static String showMarkSuccess(Task task) {
        return "Gotcha. Just marked this task as done:\n" + task;
    }

    /**
     * Prints the success message when the given task in the list has been marked as not completed.
     * @param task the task that has been marked as not completed
     */
    public static String showUnmarkSuccess(Task task) {
        return "Gotcha. Just marked this task as not done:\n" + task;
    }

    /**
     * Prints success message when the given task has been added to the list.
     * @param task the task that was added to the list of tasks
     * @param list the list of tasks
     */
    public static String showAddTaskSuccess(Task task, TaskList list) {
        return "Gotcha. Just added this task to the list:\n" + task + "\n" + showListLength(list);
    }

    /**
     * Prints success message when the given task has been removed from the list.
     * @param task the task that was removed from the list of tasks
     * @param list the list of tasks
     */
    public static String showDeleteSuccess(Task task, TaskList list) {
        return "Uh huh. Just removed this task:\n" + task + "\n" + showListLength(list);
    }

    /**
     * Prints all the elements in the provided list of tasks with numerical labels.
     * @param list the list of tasks to print
     */
    public static String showListState(TaskList list) {
        StringBuilder sb = new StringBuilder();
        sb.append("Uh huh yeah. Here's your list yo:").append(System.lineSeparator());
        for (int i = 1; i <= list.size(); i++){
            sb.append(i).append(". ").append(list.get(i - 1)).append(System.lineSeparator());
        }
        sb.append(System.lineSeparator()).append(showListLength(list));
        return sb.toString();
    }

    public static String showListLength(TaskList list) {
        return "You have " + list.size() + " tasks left. Anything else?";
    }

    public static String showFindListState(TaskList list, String keyword) {
        StringBuilder sb = new StringBuilder("Righto, here are the tasks that contain the word \""
                + keyword + "\":");
        for (int i = 1; i <= list.size(); i++){
            sb.append(i).append(". ").append(list.get(i - 1));
            if (i != list.size()) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public static boolean isError(String input) {
        String firstWord = input.substring(0,4);
        if (firstWord.equals("Woah")) {
            return true;
        } else {
            return false;
        }
    }

    public static String showHelp() {
        StringBuilder helpString = new StringBuilder();
        helpString.append("Saul Goodman, here's a list of all the commands you can make:")
                .append(System.lineSeparator())
                .append(showAddTaskCommands())
                .append(showOperationCommands());
        return helpString.toString();
    }

    private static String showAddTaskCommands() {
        StringBuilder sb = new StringBuilder();
        sb.append("New todo Task: task <title>")
                .append(System.lineSeparator())
                .append("New Deadline: deadline <title> /by <date>")
                .append(System.lineSeparator())
                .append("New Event: event <title> /from <start date> /to <end date>")
                .append(System.lineSeparator());
        return sb.toString();
    }

    private static String showOperationCommands() {
        StringBuilder sb = new StringBuilder();
        sb.append("View entire list: list")
                .append(System.lineSeparator())
                .append("Delete an Entry: delete <index>")
                .append(System.lineSeparator())
                .append("Mark an Entry as Completed: mark <index>")
                .append(System.lineSeparator())
                .append("Unmark an Entry: unmark <index>")
                .append(System.lineSeparator())
                .append("Find entries based on Keyword: find <keyword>")
                .append(System.lineSeparator());
        return sb.toString();
    }

    public static String showUndoSuccess(TaskList list) {
        return "Alright yo, just dialed back the list to its previous iteration. Here's what it looks like now:\n" +
                        showListState(list);
    }
}
