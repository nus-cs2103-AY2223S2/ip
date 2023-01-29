package Duke.Commands;
import Duke.Commands.Tasks.Deadline;
import Duke.Commands.Tasks.Event;
import Duke.Commands.Tasks.Task;
import Duke.Commands.Tasks.ToDo;
import Duke.dukeexception.DukeException;

import java.util.ArrayList;

public class Add extends Command {
    private final Task task;

    public Add(String message, Task task) {
        super(message);
        this.task = task;
    }

    @Override
    public void execute(ArrayList<Task> toDoList) {
        toDoList.add(this.task);
    }

    @Override
    public String toString() {
        return String.format("Got it! I've added this task:\n" + "    %s\n",
                this.task.toString());
    }
}
