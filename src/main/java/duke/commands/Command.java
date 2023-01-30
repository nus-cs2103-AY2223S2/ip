package duke.commands;
import duke.exceptions.DukeEmptyInputException;
import duke.exceptions.DukeInvalidInputException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

public abstract class Command {
    protected String input;

    public Command(String input) {
        this.input = input;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidInputException, DukeEmptyInputException;

    public boolean isExit() {
        return false;
    }
}
