package command;

import util.DukeException;

public class ByeCommand extends Command {
    @Override
    public void executeCommand() throws DukeException {
        System.out.println("ByeBye! Come play with me again!");
    }
}
