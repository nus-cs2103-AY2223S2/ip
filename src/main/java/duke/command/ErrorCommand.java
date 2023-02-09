package duke.command;

import duke.exception.DukeException;

public class ErrorCommand extends Command {
    private final String error;

    public ErrorCommand(String error) {
        this.error = error;
    }

    @Override
    public String execute() {
        return error;
    }
}
