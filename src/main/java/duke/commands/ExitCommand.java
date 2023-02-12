package duke.commands;

import duke.components.Storage;
import duke.components.TaskList;
import duke.exceptions.DukeException;

import java.util.ArrayList;

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
