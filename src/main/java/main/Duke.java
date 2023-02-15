package main;

import command.Command;

/**
 * Runs the application.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private static final String filePath = "tasks.txt";

    /**
     * Constructs Duke task manager.
     */
    public Duke() {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            storage.openFile();
            taskList = new TaskList(storage.loadFromFile());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Takes in user input and runs task manager until exit is called.
     */
    public String getResponse(String input) {
        String str;
        try {
            ui.startOfInput();
            Command c = Parser.parse(input);
            c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            ui.outputError(e.getMessage());
        } finally {
            str = ui.endOfInput();
        }
        return str;
    }
}