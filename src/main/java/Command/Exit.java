package command;

import storage.Storage;

public class Exit extends Command {

    public Exit(Storage s, String c) {
        super(s, c);
        exit();
    }

}
