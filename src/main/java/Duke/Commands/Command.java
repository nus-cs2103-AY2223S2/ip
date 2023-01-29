package Duke.Commands;

import Duke.Commands.Tasks.Task;
import Duke.TaskList;

import java.util.ArrayList;

public abstract class Command {
    // message is the command input
    private final String message;

    public Command(String message) {
        this.message = message;
    }

    abstract public void execute(TaskList toDoList);
}
