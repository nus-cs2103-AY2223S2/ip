package duke.instruction;

import duke.exception.GeneralDukeException;
import duke.customization.*;
import duke.task.TaskList;

public class ExitInstruction extends GeneralDukeInstruction {
    public static String reg = "bye";
    private static final DisplayFormat format = new DisplayFormat(50, 4);
    @Override
    public void run(TaskList list) throws GeneralDukeException {
        format.displayWithBar("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
