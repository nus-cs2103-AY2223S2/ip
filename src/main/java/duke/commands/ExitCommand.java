package duke.commands;

import java.util.ArrayList;

import duke.components.Storage;
import duke.components.TaskList;
import duke.exceptions.DukeException;

/**
 * This is the ExitCommand class to represent the exit command parsed by Parser.
 * Encapsulates the information needed to signal to Duke to exit.
 */
public class ExitCommand extends Command {

    public ExitCommand(ArrayList<String> tokens) {
        super(tokens);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        storage.updateStorage(tasks);
        return "Bye! See you soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
