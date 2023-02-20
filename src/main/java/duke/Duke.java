package duke;

import duke.command.Command;

/**
 * Represents a duke chatbot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns duke object.
     *
     * @param filePath path for backup file.
     */
    public Duke(String filePath) {
        assert filePath != "" : "filePath is non empty";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.readArray();
        } catch (DukeException e) {
            //ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the duke chatbot.
     */
    public String greet() {
        return ui.greet();
    }

    public String bye() {
        return ui.bye();
    }

    public String getResponse(String fullCommand) {
        try {
            Command command = Parser.parse(fullCommand);
            String output = command.execute(tasks, ui, storage);
            tasks.setLastCommand(command.inverseCommand(tasks));
            return output;
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return "I do not understand your command, please try again.";
    }


}
