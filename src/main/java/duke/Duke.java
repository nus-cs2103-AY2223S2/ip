package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.commands.CommandInput;
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

    public Command getCommand(String commandInput) {
        return CommandInput.getCommandFromInput(commandInput, this.tasks);
    }

    public boolean isSuccessfulExecution(String response) {
        return response.startsWith("I couldn't do what you asked for.");
    }
}

