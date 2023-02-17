package duke.helpers;

/**
 * Handles User Interface. Intermediary between main program and instruction processing.
 *
 * @author jengoc415
 */
public class UI {
    private Parser parser;
    private TaskList taskList;

    /**
     * Constructor for UI. Creates Parser for logic processing.
     *
     * @param taskList list of tasks.
     */
    public UI(TaskList taskList) {
        this.parser = new Parser(taskList);
        this.taskList = taskList;
    }

    /**
     * Passes received instruction to parser to process.
     * Sets isBye to true if bye command received.
     *
     * @param instr Full instruction keyed in by user.
     */
    public String process(String instr) {
        return parser.parse(instr.toLowerCase());
    }

    /**
     * Pre-written greeting message to greet users during boot up.
     */
    public static String greeting() {
        String msg = "Hello! I'm DUKE! How can I help you today?\n";
        return msg;
    }
}
