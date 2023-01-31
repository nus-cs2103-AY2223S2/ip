package leo.command;

import leo.storage.Storage;

/**
 * Represents an unmark command input by user.
 */
public class UnmarkCommand extends Command {

    /**
     * Constructor for creating an UnmarkCommand object.
     *
     * @param s Storage to store the task.
     * @param c Description of the task.
     */
    public UnmarkCommand(Storage s, String c) {
        super(s, c);
        int num = extractTaskNum();
        s.unmark(num);
    }

}
