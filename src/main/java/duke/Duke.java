package duke;

import duke.command.Command;
import duke.exceptions.EmptyCommandException;
import duke.exceptions.IncorrectFileFormatException;

import java.io.FileNotFoundException;

public class Duke { // a guy who receives user input, and gives duke reply

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String welcomeMsg;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
            this.welcomeMsg = ui.showWelcome();
        } catch (FileNotFoundException | IncorrectFileFormatException e) {
            tasks = new TaskList();
            this.welcomeMsg = ui.showWelcome(e);
        } catch (Exception e) { //catch all exception to prevent crashing
            tasks = new TaskList();
            this.welcomeMsg = ui.showWelcome(e);
        }
    }

    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public String process(String fullCommand) throws EmptyCommandException{
        try {
            Command c = Parser.parse(fullCommand);
            return c.execute(tasks, ui, storage);
            // deal with bye
        } catch (EmptyCommandException e) {
            throw e;
        } catch (Exception e) { // CATCH ALL EXCEPTION BAD
            return ui.showError(e); // or e.getMessage()
        }
    }
}