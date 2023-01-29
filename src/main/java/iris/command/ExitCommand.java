package iris.command;

import iris.TaskList;
import iris.TaskStore;
import iris.Ui;

/**
 * closes the chat
 */
public class ExitCommand extends Command {
    private static final String EXIT_TEXT = "Bye! Hope to see you soon!";

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnd() {
        return true;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, TaskStore taskStore) {
        Ui.output(EXIT_TEXT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof ExitCommand;
    }
}
