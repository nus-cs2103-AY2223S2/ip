package duke.command;

import duke.DukeResponse;

/**
 * Represents a failure of parse to parse input into a reasonable command.
 */
public class ErrorCommand extends Command {
    private final String error;

    /**
     * Constructs an error command with the given error message.
     *
     * @param error
     */
    public ErrorCommand(String error) {
        this.error = error;
    }

    @Override
    public DukeResponse execute() {
        assert error != null;
        return new DukeResponse(error);
    }
}
