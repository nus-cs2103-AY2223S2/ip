package peppa;

import peppa.commands.Command;

/**
 * Represents the chatbot application.
 */
public class Peppa {
    public static final String FILE_PATH = "data/todo.txt";
    private Ui screen;
    private TaskList tasks;
    private Storage storage;

    public Peppa() {}

    /**
     * Starts the chatbot application.
     */
    public Peppa(String filepath) {
        this.screen = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(filepath);
        storage.loadData(tasks, screen);
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            return c.execute(tasks, screen, storage);
        } catch (PeppaException e) {
            return e.getMessage();
        }
    }
}
