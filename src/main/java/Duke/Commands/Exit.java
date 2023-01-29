package Duke.Commands;

import Duke.Commands.Tasks.Task;
import Duke.TaskList;

import java.util.ArrayList;

public class Exit extends Command {
    public Exit(String message) {
        super(message);
    }
    @Override
    public void execute(TaskList toDoList) {
        // nothing to do
    }
}
