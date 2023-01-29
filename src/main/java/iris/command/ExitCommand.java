package iris.command;

import iris.TaskList;
import iris.Ui;
import iris.TaskStore;

/**
 * closes the chat
 */
public class ExitCommand extends Command{
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnd() {
        return true;
    }
    private static final String EXIT_TEXT = "Bye! Hope to see you soon!";

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
