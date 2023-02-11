package runner;

/**
 * Class for Duke.
 */
public class Duke {
    protected final Storage storage;
    protected final TaskList taskList;
    private final Parser parser;

    /**
     * Constructor for a Duke object.
     */
    public Duke() {
        this.taskList = new TaskList();
        this.storage = new Storage(taskList);
        this.parser = new Parser(this);
        storage.loadList();
    }

    public String getResponse(String input) {
        return parser.handle(input);
    }
}
