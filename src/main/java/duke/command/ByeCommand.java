package duke.command;

import duke.DukeResponse;
import duke.MessageGenerator;

/**
 * Represents a bye command that when executed returns a DukeResponse that signals application exit.
 */
public class ByeCommand extends Command {

    @Override
    public DukeResponse execute() {
        return new DukeResponse(MessageGenerator.genByeMsg(), true);
    }
}
