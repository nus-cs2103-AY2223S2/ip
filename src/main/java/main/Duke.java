package main;

import command.Command;
import exception.DukeException;

/**
 * Main class for the project
 */
public class Duke {
    private Storage storage;
    private TaskList list;
    private Parser parser;
    private Ui ui;

    /**
     * Creates an instance of Duke
     * 
     * @param filePath the relative path for the storage to store the task
     */
    public Duke(String filePath) {
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        try {
            this.list = new TaskList(storage.load());
        } catch (Exception e) {
            this.list = new TaskList();
        }
        this.ui = new Ui();
    }

    /**
     * starts the programme
     */
    public String run(String input) {
        try {
            Command command = this.parser.parse(input);
            command.execute(list, ui, storage);
            return ui.print();
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    public String getResponse(String input) {
        return this.run(input);
    }

    public String printHello() {
        return ui.printHello();
    }
}
