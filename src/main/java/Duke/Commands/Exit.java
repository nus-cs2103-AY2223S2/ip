package Duke.Commands;

import Duke.Commands.Tasks.Task;

import java.util.ArrayList;

public class Exit extends Command {
    public Exit(String message) {
        super(message);
    }
    @Override
    public void execute(ArrayList<Task> toDoList) {
        // nothing to do
    }
}
