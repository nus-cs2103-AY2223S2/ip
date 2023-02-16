package duke.command;

import duke.TaskList;
import duke.exception.DukeException;

public abstract class Command {
    protected String input;

    public Command(String input) {
        this.input = input;
    }

    public abstract TaskList execute(TaskList tasks) throws DukeException;
}
