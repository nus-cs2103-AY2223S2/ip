package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Duke{
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.loadData());
        } catch (DukeException e) {
            Ui.showFatalError(e.getMessage());
        } catch (IOException e) {
            Ui.showFatalError("Error in connecting to file");
        }
    }

    protected String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                System.exit(0);
            }
            return c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Date must be in the format dd/MM/yyyy";
        }
    }
}
