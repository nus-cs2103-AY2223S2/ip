package duke.duke;

/**
 * The main driver class to run the Duke program
 */
public class Duke {
    private TaskList data;
    private Ui ui;

    /**
     * Loads preexisting data into the program, then reads user inputs
     * for commands.
     */
    public void run() {
        this.data = Storage.populateList();
        this.ui = new Ui(new Parser(data));
        this.ui.readInput();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
