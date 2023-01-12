package duke.instruction;

import duke.task.DeadlineTask;
import duke.task.GeneralDukeTask;

public class AddDeadlineTaskInstruction extends AddTaskInstruction{
    private static final String reg = "^deadline (.)*?";

    public AddDeadlineTaskInstruction(DeadlineTask task) {
        super(task);
    }
}
