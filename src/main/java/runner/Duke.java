package runner;

/**
 * Class for Duke.
 */
public class Duke {
    protected int exit;
    protected final Storage storage;
    protected final TaskList taskList;

    /**
     * Constructor for a Duke object.
     */
    public Duke() {
        this.taskList = new TaskList();
        this.storage = new Storage(taskList);
        storage.loadList();
    }

    public String getResponse(String input) {
        return new Parser(this).handle(input);
    }
}
