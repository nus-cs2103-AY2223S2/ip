package duke;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.ui.Ui;
import duke.commands.*;
import duke.storage.*;
import duke.tasks.*;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readTaskList());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getResponse(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }

        return ui.getResponses();
    }

}


