package duke.command;

import java.util.Objects;

/**
 * A {@code CommandResult} encapsulates the result of running a command.
 */
public class CommandResult {

    private String result;

    public CommandResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(result);
    }

    @Override
    public String toString() {
        return "Result of command: " + result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CommandResult)) {
            return false;
        }
        CommandResult res = (CommandResult) obj;
        return Objects.equals(result, res.result);
    }
}
