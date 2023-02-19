package tigerlily.util;

import tigerlily.commands.Command;
import tigerlily.exceptions.DukeExceptions;
import tigerlily.tasks.TaskList;

public class Tigerlily {
    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Tigerlily
     * @param filePath the file path needed for Storage
     */
    public Tigerlily(String filePath) {
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasks(parser));
        } catch (DukeExceptions e) {
            ui.showError(e);
            taskList = new TaskList();
        }
        this.ui = new Ui();
    }

    public String getResponse(String input) {
        try {
            Command thisCommand = parser.handleCommand(input);
            return thisCommand.execute(taskList, ui, storage);
        } catch (DukeExceptions e) {
            return ui.showError(e);
        }
    }
}