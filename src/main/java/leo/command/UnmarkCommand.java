package leo.command;

import leo.leoexception.LeoException;
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
    }

    @Override
    public String execute() throws LeoException {
        int num = extractTaskNum();
        assert storage.getTask(num) != null;
        return storage.unmark(num);
    }

}
