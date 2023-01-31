package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    protected String cmdWord;
    private static final Command NONE = new NoCommand();

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }

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
}
