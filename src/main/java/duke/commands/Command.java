package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.ui.UserInterface;

public abstract class Command {
    public abstract void execute(TaskList list, UserInterface ui) throws DukeException;
}
