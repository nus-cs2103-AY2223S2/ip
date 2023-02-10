package duke.command;

import duke.DukeResponse;
import duke.MessageGenerator;

public class ByeCommand extends Command {

    @Override
    public DukeResponse execute() {
        return new DukeResponse(MessageGenerator.genByeMsg(), true);
    }
}
