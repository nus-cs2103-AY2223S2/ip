package duke.instruction;

import duke.exception.GeneralDukeException;
import duke.task.TaskList;

public class ExitInstruction extends GeneralDukeInstruction {
    @Override
    public void run(TaskList list) throws GeneralDukeException {
        format.displayWithBar("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
