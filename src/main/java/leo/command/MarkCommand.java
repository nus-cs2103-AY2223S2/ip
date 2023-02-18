package leo.command;

import leo.leoexception.LeoException;
import leo.storage.Storage;

/**
 * Represents a mark command input by user.
 */
public class MarkCommand extends Command {

    /**
     * Constructor to create a MarkCommand object.
     *
     * @param s Storage to store the task.
     * @param c Description of the task.
     */
    public MarkCommand(Storage s, String c) {
        super(s, c);
    }

    @Override
    public String execute() throws LeoException {
        int num = extractTaskNum();
        assert storage.getTask(num) != null;
        return storage.mark(num);
    }

}
