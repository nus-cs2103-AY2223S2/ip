package leo.command;

import leo.storage.Storage;

public class ExitCommand extends Command {

    public ExitCommand(Storage s, String c) {
        super(s, c);
        exit();
    }

}
