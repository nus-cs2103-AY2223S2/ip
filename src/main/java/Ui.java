public class Ui {

    private static final String GREET = "Hey! I'm your friend, Chattime!  (•◡•) /\n"
            + "     How can I help you *^*";
    private static final String LINE = "---------------------------------------------------------------------" +
            "******************CHATTIME";
    private static final String ERR_LINE = "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" +
            "******************CHATTIME";
    private static final String BYE = "Bye bye >^<! Visit me again when you need me ~";
    private static final String LOGO = "      ___\n"
                        + "     /*  \\    \\(˘◡˘)/\n"
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

    public Ui() {
        this.isRunning = true;
        this.uiInit();
    }

    public void uiInit() {
        System.out.println("Welcome to\n" + LOGO);
        replyUser(GREET);
    }

    public void replyUser(String message) {
        System.out.println("    " + LINE);
        System.out.println("     " + message);
        System.out.println("    " + LINE);
    }

    public void printAddTask(Task task, String totalTask) {
        replyUser(String.format("Got it! I've added this task:\n       %s\n     %s", task, totalTask));
    }

    public void notDoneMessage(Task task) {
        replyUser(String.format("Arghh! This job is not done yet:\n       %s", task));
    }

    public void doneMessage(Task task) {
        replyUser(String.format("Congrats! You've done this job:\n       %s", task));
    }

    public void removeTaskMsg(Task task, String totalTask) {
        replyUser(
                String.format("Okay!!! I've removed this task for you:\n       %s\n     %s", task, totalTask));
    }

    public void printError(String errMsg) {
        System.out.println("    " + ERR_LINE);
        System.out.println("     " + errMsg);
        System.out.println("    " + ERR_LINE);
    }

    public void emptyList() {
        printError("Can't find anything in the list @~@");
    }

    public boolean getExitStatus() {
        return isRunning;
    }

    public void endChat() {
        isRunning = false;
    }

    public void exit() {
        replyUser(BYE);
    }
}
