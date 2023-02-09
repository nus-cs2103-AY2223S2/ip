package duke.command;

import duke.DukeResponse;

public class ByeCommand extends Command {

    @Override
    public DukeResponse execute() {
        return new DukeResponse("goodbye", true);
    }
}
