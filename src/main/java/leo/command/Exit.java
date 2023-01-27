package leo.command;

import leo.storage.Storage;

/**
 * Represents an exit command input by user.
 */
public class Exit extends Command {

    public Exit(Storage s, String c) {
        super(s, c);
        exit();
    }

}
