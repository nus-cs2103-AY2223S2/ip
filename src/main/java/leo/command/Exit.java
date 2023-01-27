package leo.command;

import leo.storage.Storage;

public class Exit extends Command {

    public Exit(Storage s, String c) {
        super(s, c);
        exit();
    }

}
