package duke.instruction;

import duke.task.TodoTask;

public class AddToDoTaskInstruction extends AddTaskInstruction {
    public AddToDoTaskInstruction(TodoTask task) {
        super(task);
    }
}
