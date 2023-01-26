package duke.commands;

import duke.DukeException;
import duke.TaskList;

public abstract class Command {
    protected String[] tokens;
    public Command(String[] tokens) {
        this.tokens = tokens;
    }

    public abstract String execute(TaskList tasks) throws DukeException;

    protected abstract void validateCommand() throws DukeException;
}
