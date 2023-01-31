package leo.command;

import leo.storage.Storage;

/**
 * Represents a deletion command input by user.
 */
public class DeleteCommand extends Command {

    /**
     * Constructor to create a DeleteCommand object.
     *
     * @param s Storage to store the task.
     * @param c Description of the task.
     */
    public DeleteCommand(Storage s, String c) {
        super(s, c);
        int num = extractTaskNum();
        s.delete(num);
    }

}
