package runner;

import task.Task;

/**
 * Class for Riddle.
 */
public class Riddle {
    public final Storage storage;
    public final TaskList taskList;
    private final Parser parser;
    private String recentInput = "";
    private Task deletedTask;

    /**
     * Constructor for a Riddle object.
     */
    public Riddle() {
        this.taskList = new TaskList();
        this.storage = new Storage(taskList);
        this.parser = new Parser(this);
        storage.loadList();
    }

    /**
     * Update recentInput.
     * @param input New input.
     */
    public void updateInput(String input) {
        recentInput = input;
    }

    /**
     * Update deletedTask.
     * @param task
     */
    public void updateDeleted(Task task) {
        deletedTask = task;
    }

    /**
     * @return Most recent deleted task.
     */
    public Task getDeletedTask() {
        return deletedTask;
    }

    /**
     * @return Most recent input(command).
     */
    public String getRecentInput() {
        return recentInput;
    }

    /**
     * @param input User input.
     * @return Answer of Riddle.
     */
    public String getResponse(String input) {
        return parser.handle(input);
    }
}
