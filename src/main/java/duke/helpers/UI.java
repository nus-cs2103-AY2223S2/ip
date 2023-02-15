package duke.helpers;

/**
 * Handles User Interface. Intermediary between main program and instruction processing.
 *
 * @author jengoc415
 */
public class UI {
    private static final String LINE = "____________________________________________________________\n";
    private boolean terminated;
    private Parser parser;
    private TaskList taskList;

    /**
     * Constructor for UI. Creates Parser for logic processing.
     *
     * @param taskList list of tasks.
     */
    public UI(TaskList taskList) {
        this.terminated = false;
        this.parser = new Parser(taskList);
        this.taskList = taskList;
    }

    /**
     * Passes received instruction to parser to process.
     * Sets isBye to true if bye command received.
     *
     * @param instr Full instruction keyed in by user.
     */
    public void process(String instr) {
        boolean isBye = parser.parse(instr.toLowerCase());
        if (isBye) {
            this.terminated = true;
        }
    }

    /**
     * Checks status of program.
     *
     * @return true if bye command is received.
     */
    public boolean isTerminated() {
        return this.terminated;
    }

    /**
     * Pre-written greeting message to greet users during boot up.
     */
    public void greeting() {
        String msg = "Hello! I'm\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";

        if (this.taskList.isEmpty()) {
            msg += "You have no previously saved tasks.\n";
        } else {
            msg += "Your previously saved tasks have been successfully loaded!\n";
        }

        msg += "How can I help you today?\n";

        printWithLines(msg);
    }

    /**
     * Template to print all of Duke's comments.
     *
     * @param msg Message to print within lines.
     *            Make sure to include \n character.
     */
    public static void printWithLines(String msg) {
        System.out.println(LINE + msg + LINE);
    }
}
