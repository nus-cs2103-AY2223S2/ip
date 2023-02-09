package chattime.ui;

import chattime.task.Task;

/**
 * Represents UI for the bot.
 */
public class Ui {
    private static final String LOGO = "      ___\n"
                        + "     /*  \\    \\(*.*)/\n"
                        + "    /::\\   \\          __\n"
                        + "   /:/::\\   \\     /*   \\\n"
                        + "  /:/  \\:\\   \\   \\ : \\  \\\n"
                        + " /:/__/\\:\\__\\  \\ : \\  \\\n"
                        + " \\:\\ \\    \\/__/ / ::  \\   \\\n"
                        + "  \\:\\ \\            /: /\\    \\__\\\n"
                        + "   \\:\\ \\*H*A*T/ /  \\/__/*I*M*E\n"
                        + "    \\:\\_\\    / :/  /\n"
                        + "      \\/_/   \\/_/\n";

    private static final String GUIDE = "If you need my help, call me with these!\n\n"
            + "todo <task> -- to add todo stuff\n\n"
            + "deadline <task> /by <date in yyyy-mm-dd or yyyy-mm-dd hh:mm> -- to add todo stuff with deadline\n\n"
            + "event <task> /from <date in yyyy-mm-dd or yyyy-mm-dd hh:mm> /to <date in yyyy-mm-dd or yyyy-mm-dd hh:mm>"
            + " -- to add event with start datetime to end datetime\n\n"
            + "list -- to list all current stored tasks\n\n"
            + "listTime <date in yyyy-mm-dd> -- to list all current tasks within the specified date\n\n"
            + "schedule <date in yyyy-mm-dd> -- to list all sorted unmarked tasks in the specified date\n\n"
            + "mark <index of task> -- to mark the specified task as done\n\n"
            + "unmark <index of task> -- to unmark the specified task\n\n"
            + "delete <index of task> -- to delete the specified task from list\n\n"
            + "bye -- to say goodbye to me and end our chat :(\n\n"
            + "help -- to view this guide";

    private static final String GREET = "Hey! I'm your friend, Chattime!(# v #) /\n"
            + "How can I help you today *^*\n\nType `help` and I will come to you %v%";

    private static final String BYE = "Bye bye >^<! Visit me again when you need me ~";
    private static final String SYSTEM_ERR_MSG = "Sorry I'm currently offline :,: \nI'm sick due to :\n";
    private static final String STORAGE_ERR_MSG = "My memory messed up @^@ I think I need to leave now #.#";

    private boolean isRunning;
    private boolean isNormalInit;
    private String errorMsg;

    /**
     * Creates UI objects, sets bot running status to true.
     */
    public Ui() {
        isRunning = true;
        isNormalInit = true;
    }

    /**
     * Returns initial UI message to user.
     *
     * @return Greeting message, if storage error, return error alert.
     */
    public String initUi() {
        if (isNormalInit) {
            return "Welcome to\n" + LOGO + "\n" + GREET;
        } else {
            return SYSTEM_ERR_MSG + errorMsg;
        }
    }

    /**
     * Reports storage error.
     */
    public void reportSystemError(String errorMessage) {
        isNormalInit = false;
        errorMsg = errorMessage;
    }

    /**
     * Getter for isNormalInit.
     *
     * @return Initiation status of storage.
     */
    public boolean getInitStatus() {
        return isNormalInit;
    }

    /**
     * Alerts user storage error.
     *
     * @return Storage error message.
     */
    public String reportStorageError() {
        return STORAGE_ERR_MSG;
    }

    /**
     * Returns task added recently.
     *
     * @param task Task added recently.
     * @param totalTask Message about total number of available tasks.
     */
    public String printAddTask(Task task, String totalTask) {
        return String.format("Got it! I've added this task:\n   %s\n%s", task, totalTask);
    }

    /**
     * Returns unmarked task message.
     *
     * @param task Task unmarked recently.
     */
    public String replyNotDoneMessage(Task task) {
        return String.format("Arghh! This job is not done yet:\n       %s", task);
    }

    /**
     * Returns marked task message.
     *
     * @param task Task marked recently.
     */
    public String replyDoneMessage(Task task) {
        return String.format("Congrats! You've done this job:\n       %s", task);
    }

    /**
     * Returns remove task message.
     *
     * @param task Task removed recently.
     * @param totalTask Message about total number of available tasks.
     */
    public String replyRemoveTaskMsg(Task task, String totalTask) {
        return String.format("Okay!!! I've removed this task for you:\n       %s\n     %s", task, totalTask);
    }

    /**
     * Returns error message to user when operation on empty task list is requested.
     */
    public String warnEmptyList() {
        return "Can't find anything in the list @~@";
    }

    /**
     * Returns user guide.
     */
    public String alertDuplicate() {
        return "AHH?! Seems that this job is added before ><";
    }

    /**
     * Gets the running status of bot.
     *
     * @return true if bot still running, false if bye command encountered.
     */
    public boolean getExecuteStatus() {
        return isRunning;
    }

    /**
     * Sets running status to false.
     */
    public void endChat() {
        isRunning = false;
    }

    /**
     * Returns bot detected error message to user with specific format.
     *
     * @param errMsg Bot error message.
     */
    public String printError(String errMsg) {
        return errMsg;
    }

    /**
     * Returns exit message to user.
     */
    public String exit() {
        return BYE;
    }

    /**
     * Returns user guide.
     */
    public String printGuide() {
        return GUIDE;
    }
}
