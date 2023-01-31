package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.commands.CommandInput;
import duke.exceptions.CommandExecutionError;
import duke.tasks.TaskList;


/**
 * Main class where duke is initialized and runs.
 */
public class Duke {
    private TaskList tasks;

    public Duke() {
        tasks = new TaskList();
        try {
            Storage.loadFromFile(tasks);
        } catch (IOException e) {
            // Ui.showLoadingError();
        }
    }

    public void run() {
        Ui.greetUser();
        while (Ui.getIsRunning()) {
            Command command = CommandInput.getCommandFromInput(Ui.getLine(), tasks);
            try {
                command.execute();
            } catch (CommandExecutionError e) {
                Ui.displayMsg("Couldn't execute command :/ \n" + e.toString());
            } 
            Ui.getNextCommand();
        }
    }

    // Initialize and run duke
    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}
