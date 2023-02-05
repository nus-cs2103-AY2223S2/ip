package Duke.Chatbot;

/**
 * Handles the output to be printed to user.
 */
public class Ui {

    /**
     * Error message to be shown to user when an error
     * occurs when loading data.
     */
    public void showLoadingError() {
        System.out.println("Error loading data!");
    }

    /**
     * Error message to be shown to user when
     * index user specified is larger than the size of the list
     */
    public void showIndexError() {
        System.out.println("Error: Index specified must be smaller than current list size.");
    }

    /**
     * Error message to be shown to user when
     * user does not specify an index in Delete
     * mark and unmark commands
     */
    public void showIndexNotSpecifiedError() {
        System.out.println("Error: Index not specified");
    }

    /**
     * Message to be shown to user when a task has been successfully added to the list
     *
     * @param taskMessage    User representation of the task that has just been added
     * @param newTasksLength length of tasks after this addition
     */
    public void showTaskAddedMessage(String taskMessage, int newTasksLength) {
        System.out.println("Got it. I've added this task:");
        showTaskEditedMessage(taskMessage, newTasksLength);

    }


    private void showTaskEditedMessage(String taskMessage, int newTasksLength) {
        System.out.println("\t" + taskMessage);
        System.out.println("Now you have " + newTasksLength + " tasks in the list");
    }

    /**
     * Message to be shown to user on startup
     */
    public void showStartupMessage() {
        String logo = "               _     __,..---\"\"-._                 ';-,\n" +
                "        ,    _/_),-\"`             '-.                `\\\\\n" +
                "       \\|.-\"`    -_)                 '.                ||\n" +
                "       /`   a   ,                      \\              .'/\n" +
                "       '.___,__/                 .-'    \\_        _.-'.'\n" +
                "          |\\  \\      \\         /`        _`\"\"\"\"\"\"`_.-'\n" +
                "             _/;--._, >        |   --.__/ `\"\"\"\"\"\"`\n" +
                "           (((-'  __//`'-......-;\\      )\n" +
                "                (((-'       __//  '--. /\n" +
                "                          (((-'    __//\n" +
                "                                 (((-'";

        String MESSAGE_START = "What can I do for you?";

        System.out.println("Squeak squeak\n" + logo);
        System.out.println(MESSAGE_START);
    }

    /**
     * Message to be shown to user when message is successfully removed from the list
     *
     * @param taskMessage User representation of the message successfully removed
     * @param taskLength  Length of the list after this removal
     */
    public void showRemovedTaskMessage(String taskMessage, int taskLength) {
        System.out.println("Got it. I've removed this task:");
        showTaskEditedMessage(taskMessage, taskLength);
    }

    /**
     * Shows the User representation of the tasks that are currently in the list
     *
     * @param tasks User representation of the tasks that are currently in the list
     */
    public void showTasksMessage(String tasks) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(tasks);
    }

    /**
     * Error message to be shown when task the user is trying to mark
     * has already been marked
     */
    public void showTaskStateCompletedError() {
        System.out.println("Task is already marked as done.");
    }

    /**
     * Message to be shown when the task a user is trying to mark
     * has successfully been marked.
     *
     * @param taskMessage User representation of the task the user has just marked
     */
    public void showTaskCompletedMessage(String taskMessage) {
        System.out.println("Nice! I've marked this task as done:");

        System.out.println("\t" + taskMessage);
    }

    /**
     * Error message to be shown to the user if the task a user is trying to unmark
     * is currently unmarked
     */
    public void showTaskStateIncompletedError() {
        System.out.println("Task is already marked as incompelted.");
    }

    /**
     * Message to be shown to the user if the task a user is trying to unmark
     * has been successfully unmarked
     *
     * @param taskMessage User Representation of the task the user has just unmarked.
     */
    public void showTaskIncompletedMessage(String taskMessage) {
        System.out.println("I have maked this task as incomplete:");
        System.out.println("\t" + taskMessage);

    }

    /**
     * Error message to be shown to the user if no Description is given
     * following a Task command.
     */
    public void showEmptyDescriptionError() {
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");

    }

    /**
     * Error message to be shown to the user if Deadline Command is called
     * without the appropriate commands
     */
    public void showIncompleteDeadlineArgumentsError() {
        System.out.println("Error: Invalid number of args. Pls add a /by in your command, " +
                "or ensure task name is not not empty");
    }

    /**
     * Error message to be shown to the user if Event Command is called
     * without the appropriate commands
     */
    public void showIncompleteEventArgumentsError() {
        System.out.println("Error: Invalid number of args. Pls add a /from and /to in your command," +
                " or ensure task name is not not empty");
    }

    /**
     * Error message to be shown to the user if Duke does not recognise
     * the command that has been entered
     */
    public void showUnrecognisedCommandError() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

    }

    /**
     * Error message to be shown to user if Command given included an invalid datetime
     */
    public void showDateTimeParseError() {
        System.out.println("Error: Unable to parse datetime.");
    }

    /**
     * Error message to be shown to user if Save data contains unimplemented task types
     */
    public void showUnimplementedTaskTypeError() {
        System.out.println("Error: Task type is not implemented");
    }

    /**
     * Message to be shown to user if Deleteall command is run
     */
    public void showRemoveAllTasksMessage() {
        System.out.println("Deletion complete!");
    }

    /**
     * Message to be shown to user if list is called
     * when no tasks are in the list.
     */
    public void showNoTasksMessage() {
        System.out.println("There are no tasks to print!");
    }

    /**
     * Error message to be shown when Find command is called
     * when no Keyword is specified
     */
    public void showEmptyFindKeywordError() {
        System.out.println("Error: Find has no keyword to find for");

    }

    /**
     * Message to be shown when Find command is called
     *
     * @param args Keyword entered into find command
     */

    public void showFindMessage(String args) {
        System.out.println("Finding tasks related to keyword: " + args);
    }
}
