package duke.instruction;

import duke.task.DeadlineTask;

public class AddDeadlineTaskInstruction extends AddTaskInstruction{
    public AddDeadlineTaskInstruction(DeadlineTask task) {
        super(task);
    }
}
