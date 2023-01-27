package leo.command;

import leo.leoException.NoTaskFoundException;
import leo.storage.Storage;
import leo.ui.Ui;

/**
 * Represents a list command input by user.
 */
public class ListCommand extends Command {

    public ListCommand(Storage s, String c) {
        super(s, c);
        s.showList();
    }

}
