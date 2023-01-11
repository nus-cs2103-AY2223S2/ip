package duke.instruction;

import duke.exception.GeneralDukeException;
import duke.customization.*;

public class ExitInstruction extends GeneralDukeInstruction {
    private static final DisplayFormat format = new DisplayFormat(50, 4);
    @Override
    public void run() throws GeneralDukeException {
        format.displayWithBar("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
