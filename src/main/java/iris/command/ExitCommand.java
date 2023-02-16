package iris.command;

import iris.TaskList;
import iris.TaskStore;

/**
 * closes the chat
 */
public class ExitCommand extends Command {
    private static final String EXIT_TEXT = "Bye! Hope to see you soon!";

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResponse(TaskList tasks, TaskStore taskStore) {
        return EXIT_TEXT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof ExitCommand;
    }
}
