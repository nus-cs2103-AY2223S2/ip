package command;

import exception.DukeException;

public class ByeCommand extends Command {
    
    @Override
    public void execute() throws DukeException {
        System.out.println("    Bye. Hope to see you again soon!");
    }
}
