package duke;

import duke.command.Command;
public class Duke {


    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        StringBuilder sb = new StringBuilder();
        try {
            Command c = Parser.parse(input);
            sb.append(c.execute(tasks, ui, storage));
        } catch (DukeException e) {
            return ui.printError(e.getMessage());
        }

        return sb.toString();
    }

}
