package duke.instruction;

import duke.exception.GeneralDukeException;
import duke.task.TaskList;

/*
 * A DukeInstruction which simple repeat the user's input
 * Obsolete after level-2
 */

public class EchoInstruction extends GeneralDukeInstruction {
    private final String echoMessage;

    public EchoInstruction(String echoMessage) {
        this.echoMessage = echoMessage;
    }

    @Override
    public void run(TaskList list) throws GeneralDukeException {
        format.displayWithBar(this.echoMessage);
    }
}
