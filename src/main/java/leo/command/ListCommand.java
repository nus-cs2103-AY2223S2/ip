package leo.command;

import leo.leoException.NoTaskFoundException;
import leo.storage.Storage;
import leo.ui.Ui;

public class ListCommand extends Command {

    public ListCommand(Storage s, String c) {
        super(s, c);
        try {
            s.showList();
        } catch (NoTaskFoundException e) {
            Ui.displayMessage(Ui.leoResponse(e.getMessage()));
        }
    }

}
