package runner;

import task.Task;

/**
 * Class for Duke.
 */
public class Duke {
    public final Storage storage;
    public final TaskList taskList;
    private final Parser parser;
    private String recentInput = "";
    private Task deletedTask;

    /**
     * Constructor for a Duke object.
     */
    public Duke() {
        this.taskList = new TaskList();
        this.storage = new Storage(taskList);
        this.parser = new Parser(this);
        storage.loadList();
    }

    public void updateInput(String s) {
        recentInput = s;
    }

    public void updateDeleted(Task tk) {
        deletedTask = tk;
    }

    public Task getDeletedTask() {
        return deletedTask;
    }

    public String getRecentInput() {
        return recentInput;
    }

    public String getResponse(String input) {
        return parser.handle(input);
    }
}
