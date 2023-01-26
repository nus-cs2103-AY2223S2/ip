package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Duke {
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

    public void run() {
        Ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                Ui.showError(e.getMessage());
            } catch (DateTimeParseException e) {
                Ui.showError("Date must be in the format dd/MM/yyyy");
            } finally {
                Ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
