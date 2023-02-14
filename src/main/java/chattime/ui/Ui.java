package chattime.ui;

import chattime.task.Task;

/**
 * Represents UI for the bot.
 */
public class Ui {
    private static final String LOGO = "      ___\n"
                        + "     /*  \\    \\(*.*)/\n"
                        + "    /::\\   \\           __\n"
                        + "   /:/::\\   \\      /*   \\\n"
                        + "  /:/  \\:\\   \\    \\ : \\  \\\n"
                        + " /:/__/\\:\\__\\  \\  : \\  \\\n"
                        + " \\:\\ \\    \\/__/  / ::  \\   \\\n"
                        + "  \\:\\ \\             /: /\\    \\__\\\n"
                        + "   \\:\\ \\*H*A*T/ /  \\/__/*I*M*E\n"
                        + "    \\:\\_\\     / :/  /\n"
                        + "      \\/_/    \\/_/\n";

    private static final String GUIDE = "If you need my help, call me with these! ^*^\n\n"
            + "todo <task> \n-- to add todo stuff\n\n"
            + "deadline <task> /by <date in yyyy-mm-dd or yyyy-mm-dd hh:mm> \n-- to add todo stuff with deadline\n\n"
            + "event <task> /from <date in yyyy-mm-dd or yyyy-mm-dd hh:mm> /to <date in yyyy-mm-dd or yyyy-mm-dd hh:mm>"
            + "\n-- to add event with start datetime to end datetime\n\n"
            + "list \n-- to list all current stored tasks\n\n"
            + "listTime <date in yyyy-mm-dd> \n-- to list all current tasks within the specified date\n\n"
            + "schedule <date in yyyy-mm-dd> \n-- to list all sorted unmarked tasks in the specified date\n\n"
            + "mark <index of task> \n-- to mark the specified task as done\n\n"
            + "unmark <index of task> \n-- to unmark the specified task\n\n"
            + "delete <index of task> \n-- to delete the specified task from list\n\n"
            + "bye \n-- to say goodbye to me and end our chat :(\n\n"
            + "help \n-- to view this guide";

    private static final String GREET = "Hey! I'm your friend, Chattime!(# v #) /\n"
            + "How can I help you today *^*\n\nType `help` and I will come to you %v%";

    private static final String BYE = ">^<Bye>^<Bye>^<Bye>^<Bye>^<Bye>^<Bye>^<Bye>^<Bye>^<Bye>^<Bye>^<Bye>^<Bye>^<";
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
     * @return The greeting message, if storage error, return error alert.
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
     * @return The initiation status of storage.
     */
    public boolean getInitStatus() {
        return isNormalInit;
    }

    /**
     * Alerts user storage error.
     *
     * @return The storage error message.
     */
    public String reportStorageError() {
        return STORAGE_ERR_MSG;
    }

    /**
     * Returns task added recently.
     *
     * @param task The task added recently.
     * @param totalTask The message about total number of available tasks.
     * @return The Add task message.
     */
    public String printAddTask(Task task, String totalTask) {
        return String.format("Got it! I've added this task:\n   %s\n%s", task, totalTask);
    }

    /**
     * Returns unmarked task message.
     *
     * @param task The task unmarked recently.
     * @return The confirmation of cancelling done state.
     */
    public String replyNotDoneMessage(Task task) {
        return String.format("Arghh! This job is not done yet:\n       %s", task);
    }

    /**
     * Returns marked task message.
     *
     * @param task The task marked recently.
     * @return The confirmation on done tasks.
     */
    public String replyDoneMessage(Task task) {
        return String.format("Congrats! You've done this job:\n       %s", task);
    }

    /**
     * Returns remove task message.
     *
     * @param task The task removed recently.
     * @param totalTask The message about total number of available tasks.
     * @return The confirmation of task removal.
     */
    public String replyRemoveTaskMsg(Task task, String totalTask) {
        return String.format("Okay!!! I've removed this task for you:\n       %s\n     %s", task, totalTask);
    }

    /**
     * Returns error message to user when operation on empty task list is requested.
     *
     * @return The alert of empty task list.
     */
    public String warnEmptyList() {
        return "Can't find anything in the list @~@";
    }

    /**
     * Alerts duplicate input.
     *
     * @return The reminder of duplicated tasks.
     */
    public String alertDuplicate() {
        return "AHH?! Seems that this job is added before ><";
    }

    /**
     * Gets the running status of bot.
     *
     * @return The check result of whether the bot is still running.
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
     * @param errMsg The bot error message.
     * @return The error message to user.
     */
    public String printError(String errMsg) {
        return errMsg;
    }

    /**
     * Returns exit message to user.
     *
     * @return The goodbye message to user.
     */
    public String exit() {
        return BYE;
    }

    /**
     * Returns user guide.
     *
     * @return The help guide to use this bot.
     */
    public String printGuide() {
        return GUIDE;
    }
}
