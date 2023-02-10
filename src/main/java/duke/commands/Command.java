package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UserInterface;

public abstract class Command {
    public abstract void execute(TaskList list, UserInterface ui, Storage storage) throws Exception;
}
