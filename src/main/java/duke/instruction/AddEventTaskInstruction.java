package duke.instruction;

import duke.task.EventTask;
import duke.task.GeneralDukeTask;

public class AddEventTaskInstruction extends AddTaskInstruction{
    private static final String reg = "^event (.)*?";

    public AddEventTaskInstruction(EventTask task) {
        super(task);
    }
}
