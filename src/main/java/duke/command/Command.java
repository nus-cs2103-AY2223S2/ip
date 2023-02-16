package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    private static final Command NONE = new NoCommand();
    protected String cmdOutput;

    /**
     * Runs a command.
     *
     * @param tasks Tasklist
     * @param ui UI
     * @param storage Storage
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    private static class NoCommand extends Command {
        public NoCommand() {
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {

        }
    }

    public static Command none() {
        return NONE;
    }

    public String getOutput() {
        return this.cmdOutput;
    }

    public void setOutput(String... output) {
        this.cmdOutput = String.join("\n", output);
    }
}
