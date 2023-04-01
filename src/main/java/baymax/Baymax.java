package baymax;

import commands.Command;
import commands.InvalidCommand;

import java.io.FileNotFoundException;

public class Baymax {
    private Storage storage;
    private TaskList tasks;
    private final Parser parser = new Parser();
    private final Ui ui = new Ui();

    /**
     * Constructor for Baymax.
     */
    public Baymax() {
        try {
            storage = new Storage();
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            ui.showLoadingErrorMessage();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program.
     */
    public String run(String string) {
        try {
            Command command = parser.parse(string);
            return command.execute(tasks, ui, storage);
        } catch (IllegalArgumentException e) {
            Command command = new InvalidCommand(string);
            return command.execute(tasks, ui, storage);
        }
    }
}
