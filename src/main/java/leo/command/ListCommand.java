package leo.command;

import leo.storage.Storage;

/**
 * Represents a list command input by user.
 */
public class ListCommand extends Command {

    /**
     * Constructor to create ListCommand object.
     *
     * @param s Storage to store the task.
     * @param c Description of the task.
     */
    public ListCommand(Storage s, String c) {
        super(s, c);
        s.showList();
    }

}
