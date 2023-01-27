package leo.command;

import leo.storage.Storage;

/**
 * Represents an exit command input by user.
 */
public class ExitCommand extends Command {

    public ExitCommand(Storage s, String c) {
        super(s, c);
        exit();
    }

}
