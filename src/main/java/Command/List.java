package command;

import leoException.NoTaskFoundException;
import storage.Storage;
import ui.Ui;

public class List extends Command {

    public List(Storage s, String c) {
        super(s, c);
        try {
            s.showList();
        } catch (NoTaskFoundException e) {
            Ui.displayMessage(Ui.leoResponse(e.getMessage()));
        }
    }

}
