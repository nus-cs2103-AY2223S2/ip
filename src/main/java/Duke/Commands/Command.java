package Duke.Commands;
import Duke.DukeExceptions.DukeEmptyInputException;
import Duke.DukeExceptions.DukeInvalidInputException;
import Duke.TaskList;
import Duke.Ui;
import Duke.Storage;

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
