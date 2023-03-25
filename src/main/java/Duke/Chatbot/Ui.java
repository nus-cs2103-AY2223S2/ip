package Duke.Chatbot;

/**
 * Handles the output to be printed to user.
 */
public class Ui {

    private static String recentOutput = "";

    public static String getRecentOutput() {
        String output = recentOutput;
        recentOutput = "";
        return output;
    }

    /**
     * Error message to be shown to user when an error
     * occurs when loading data.
     */
    public void showLoadingError() {
        String output = "Error loading data!\n";
        System.out.println("Error loading data!");
        recentOutput += output;
    }

    /**
     * Error message to be shown to user when
     * index user specified is larger than the size of the list
     */
    public void showIndexError() {
        String output = "Error: Index specified must be smaller than current list size.\n";
        System.out.println(output);
        recentOutput += output;
    }

    /**
     * Error message to be shown to user when
     * user does not specify an index in Delete
     * mark and unmark commands
     */
    public void showIndexNotSpecifiedError() {
        String output = "Error: Index not specified\n";
        System.out.println(output);
        recentOutput += output;
    }

    /**
     * Message to be shown to user when a task has been successfully added to the list
     *
     * @param taskMessage    User representation of the task that has just been added
     * @param newTasksLength length of tasks after this addition
     */
    public void showTaskAddedMessage(String taskMessage, int newTasksLength) {
        String output = "Got it. I've added this task:\n";
        System.out.println(output);
        recentOutput += output;
        showTaskEditedMessage(taskMessage, newTasksLength);

    }


    private void showTaskEditedMessage(String taskMessage, int newTasksLength) {
        String output = "\t" + taskMessage + "\n";
        System.out.println(output);
        recentOutput += output;
        output = "Now you have " + newTasksLength + " tasks in the list";
        System.out.println(output);
        recentOutput += output;
    }

    /**
     * Message to be shown to user on startup
     */
    public void showStartupMessage() {
        final String messageStart = "What can I do for you?\n";
        String output = "Squeak squeak\n";

        System.out.println("Squeak squeak\n");
        System.out.println(messageStart);
        recentOutput += output;
        recentOutput += messageStart;

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
        String output = "Here are the tasks in your list:\n";
        recentOutput += output;
        System.out.println(output);
        output = tasks;
        recentOutput += output;
        System.out.println(tasks);

    }

    /**
     * Error message to be shown when task the user is trying to mark
     * has already been marked
     */
    public void showTaskStateCompletedError() {
        String output = "Task is already marked as done.\n";
        recentOutput += output;
        System.out.println(output);
    }

    /**
     * Message to be shown when the task a user is trying to mark
     * has successfully been marked.
     *
     * @param taskMessage User representation of the task the user has just marked
     */
    public void showTaskCompletedMessage(String taskMessage) {
        String output = "Nice! I've marked this task as done:\n";
        System.out.println(output);
        recentOutput += output;

        output = "\t" + taskMessage + "\n";
        System.out.println("\t" + taskMessage);
        recentOutput += output;
    }

    /**
     * Error message to be shown to the user if the task a user is trying to unmark
     * is currently unmarked
     */
    public void showTaskStateIncompletedError() {
        String output = "Task is already marked as incompleted.\n";
        System.out.println(output);
        recentOutput += output;
    }

    /**
     * Message to be shown to the user if the task a user is trying to unmark
     * has been successfully unmarked
     *
     * @param taskMessage User Representation of the task the user has just unmarked.
     */
    public void showTaskIncompletedMessage(String taskMessage) {
        String output = "I have maked this task as incomplete:\n";
        System.out.println(output);
        recentOutput += output;

        output = "\t" + "taskMessage";
        System.out.println("\t" + taskMessage);
        recentOutput += output;


    }

    /**
     * Error message to be shown to the user if no Description is given
     * following a Task command.
     */
    public void showEmptyDescriptionError() {
        String output = "☹ OOPS!!! The description of a todo cannot be empty.\n";
        System.out.println(output);
        recentOutput += output;

    }

    /**
     * Error message to be shown to the user if Deadline Command is called
     * without the appropriate commands
     */
    public void showIncompleteDeadlineArgumentsError() {
        String output = "Error: Invalid number of args. Pls add a /by in your command, "
                + "or ensure task name is not not empty\n";
        System.out.println(output);
        recentOutput += output;
    }

    /**
     * Error message to be shown to the user if Event Command is called
     * without the appropriate commands
     */
    public void showIncompleteEventArgumentsError() {
        String output = "Error: Invalid number of args. Pls add a /from and /to in your command,"
                + " or ensure task name is not not empty\n";
        System.out.println(output);
        recentOutput += output;
    }

    /**
     * Error message to be shown to the user if Duke does not recognise
     * the command that has been entered
     */
    public void showUnrecognisedCommandError() {
        String output = "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        System.out.println(output);
        recentOutput += output;
    }

    /**
     * Error message to be shown to user if Command given included an invalid datetime
     */
    public void showDateTimeParseError() {
        String output = "Error: Unable to parse datetime.\n";
        System.out.println(output);
        recentOutput += output;
    }

    /**
     * Error message to be shown to user if Save data contains unimplemented task types
     */
    public void showUnimplementedTaskTypeError() {
        String output = "Error: Task type is not implemented\n";
        System.out.println(output);
        recentOutput += output;
    }

    /**
     * Message to be shown to user if Deleteall command is run
     */
    public void showRemoveAllTasksMessage() {
        String output = "Deletion complete!\n";
        System.out.println(output);
        recentOutput += output;
    }

    /**
     * Message to be shown to user if list is called
     * when no tasks are in the list.
     */
    public void showNoTasksMessage() {
        String output = "There are no tasks to print!\n";
        System.out.println(output);
        recentOutput += output;
    }

    /**
     * Error message to be shown when Find command is called
     * when no Keyword is specified
     */
    public void showEmptyFindKeywordError() {
        String output = "Error: Find has no keyword to find for\n";
        System.out.println(output);
        recentOutput += output;

    }

    /**
     * Message to be shown when Find command is called
     *
     * @param args Keyword entered into find command
     */

    public void showFindMessage(String args) {
        String output = "Finding tasks related to keyword: " + args + "\n";
        System.out.println(output);
        recentOutput += output;
    }
}
