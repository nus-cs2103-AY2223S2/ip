package duke.command;

import duke.DukeResponse;
import duke.exception.DukeException;

public class ErrorCommand extends Command {
    private final String error;

    public ErrorCommand(String error) {
        this.error = error;
    }

    @Override
    public DukeResponse execute() {
        return new DukeResponse(error);
    }
}
