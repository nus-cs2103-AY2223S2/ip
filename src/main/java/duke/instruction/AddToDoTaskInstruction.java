package duke.instruction;

import duke.task.TodoTask;

public class AddToDoTaskInstruction extends AddTaskInstruction{
    public static final String reg = "^todo (.)*?";
    public AddToDoTaskInstruction(TodoTask task) {
        super(task);
    }
}
