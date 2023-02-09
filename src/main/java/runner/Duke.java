package runner;

/**
 * Class for Duke.
 */
public class Duke {
    protected int exit;
    protected final Storage store;
    protected final TaskList taskList;

    /**
     * Constructor for a Duke object.
     */
    public Duke() {
        this.exit = 0;
        this.taskList = new TaskList();
        this.store = new Storage(taskList);
        store.loadList();
    }

    public String getResponse(String input) {
        return new Parser(this).handle(input);
    }
}
