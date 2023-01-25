package chattime.ui;

import chattime.task.Task;

/**
 * Represents UI for the bot.
 */
public class Ui {

    private static final String GREET = "Hey! I'm your friend, chattime.Chattime!  (# v #) /\n"
            + "     How can I help you *^*";
    private static final String LINE = "---------------------------------------------------------------------"
            + "******************CHATTIME";
    private static final String ERR_LINE = "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
            + "******************CHATTIME";
    private static final String BYE = "Bye bye >^<! Visit me again when you need me ~";
    private static final String LOGO = "      ___\n"
                        + "     /*  \\    \\(*.*)/\n"
                        + "    /::\\  \\     ___\n"
                        + "   /:/::\\  \\   /*  \\\n"
                        + "  /:/  \\:\\  \\  \\:\\  \\\n"
                        + " /:/__/ \\:\\__\\  \\:\\  \\\n"
                        + " \\:\\ \\   \\/__/  /::\\  \\\n"
                        + "  \\:\\ \\        /:/::\\__\\\n"
                        + "   \\:\\ \\*H*A*T/:/  \\/__/*I*M*E\n"
                        + "    \\:\\_\\    /:/  /\n"
                        + "     \\/__/   \\/__/\n";

    private boolean isRunning;

    /**
     * Creates UI objects, sets bot running status to true.
     */
    public Ui() {
        isRunning = true;
        initUi();
    }

    /**
     * Returns initial UI message to user.
     */
    public void initUi() {
        System.out.println("Welcome to\n" + LOGO);
        replyUser(GREET);
    }

    /**
     * Returns bot response message to user with specific format.
     *
     * @param message Bot response message.
     */
    public void replyUser(String message) {
        System.out.println("    " + LINE);
        System.out.println("     " + message);
        System.out.println("    " + LINE);
    }

    /**
     * Returns task added recently.
     *
     * @param task Task added recently.
     * @param totalTask Message about total number of available tasks.
     */
    public void printAddTask(Task task, String totalTask) {
        replyUser(String.format("Got it! I've added this task:\n       %s\n     %s", task, totalTask));
    }

    /**
     * Returns unmarke task message.
     *
     * @param task Task unmarked recently.
     */
    public void replyNotDoneMessage(Task task) {
        replyUser(String.format("Arghh! This job is not done yet:\n       %s", task));
    }

    /**
     * Returns marke task message.
     *
     * @param task Task marked recently.
     */
    public void replyDoneMessage(Task task) {
        replyUser(String.format("Congrats! You've done this job:\n       %s", task));
    }

    /**
     * Returns remove task message.
     *
     * @param task Task removed recently.
     * @param totalTask Message about total number of available tasks.
     */
    public void replyRemoveTaskMsg(Task task, String totalTask) {
        replyUser(
                String.format("Okay!!! I've removed this task for you:\n       %s\n     %s", task, totalTask));
    }

    /**
     * Returns bot detected error message to user with specific format.
     *
     * @param errMsg Bot error message.
     */
    public void printError(String errMsg) {
        System.out.println("    " + ERR_LINE);
        System.out.println("     " + errMsg);
        System.out.println("    " + ERR_LINE);
    }

    /**
     * Returns error message to user when operation on empty task list is requested.
     */
    public void warnEmptyList() {
        printError("Can't find anything in the list @~@");
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
     * Returns exit message to user.
     */
    public void exit() {
        replyUser(BYE);
    }
}
