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
            e.printStackTrace();
        }
    }

    public String getResponse(String commandInput) {
        Command command = CommandInput.getCommandFromInput(commandInput, this.tasks);
        try {
            return command.execute();
        } catch (CommandExecutionError e) {
            return "Couldn't execute command :/ \n" + e.toString();
        }
    }

    public boolean isSuccessfulExecution(String response) {
        return response.startsWith("Couldn't execute command :/");
    }
}

