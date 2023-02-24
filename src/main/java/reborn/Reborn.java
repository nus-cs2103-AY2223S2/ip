package reborn;

import reborn.command.Command;
import reborn.data.TaskList;
import reborn.data.exception.RebornException;
import reborn.parser.Parser;
import reborn.storage.Storage;
import reborn.ui.Ui;

/**
 * Represents Duke application.
 */
public class Reborn {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     *
     * @throws RebornException If loading of saved tasks fails.
     */
    public Reborn() throws RebornException {
        ui = new Ui();
        storage = new Storage("src/main/java/reborn/data/reborn.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (RebornException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}

