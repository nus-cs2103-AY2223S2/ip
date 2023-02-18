package leo.command;

import leo.storage.Storage;

/**
 * Represents an exit command input by user.
 */
public class ExitCommand extends Command {

    /**
     * Constructor to create an ExitCommand object.
     *
     * @param s Storage to store the task.
     * @param c Description of the task.
     */
    public ExitCommand(Storage s, String c) {
        super(s, c);
    }

    @Override
    public String execute() {
        return exit();
    }

}
