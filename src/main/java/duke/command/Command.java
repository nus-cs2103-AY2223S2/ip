package duke.command;

import duke.exceptions.DukeException;
import duke.Storage;
import duke.TaskList;

public abstract class Command {
    private static final Command NONE = new NoCommand();
    protected String cmdOutput;

    /**
     * Runs a command.
     *
     * @param tasks Tasklist
     * @param storage Storage
     */
    public abstract void execute(TaskList tasks, Storage storage) throws DukeException;

    private static class NoCommand extends Command {
        public NoCommand() {
        }

        @Override
        public void execute(TaskList tasks, Storage storage) {
        }
    }

    /**
     * Returns a pseudo-command that does nothing.
     *
     * @return The None pseudo-command
     */
    public static Command none() {
        return NONE;
    }

    /**
     * Gets the display output of a command;
     *
     * @return String representing the display output of the command
     */
    public String getOutput() {
        return this.cmdOutput;
    }

    /**
     * Sets the output of a command to be displayed.
     *
     * @param output Strings to be displayed with newline characters in-between.
     */
    public void setOutput(String... output) {
        this.cmdOutput = String.join("\n", output);
    }
}
