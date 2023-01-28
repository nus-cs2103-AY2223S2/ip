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

    private static String printTaskAdd(Task task, ArrayList<Task> toDoList) {
        return String.format("Got it! I've added this task:\n" +
                        "    %s\n" +
                        "Now you have %d tasks in the list.",
                task.toString(), toDoList.size());
    }

    @Override
    public void execute(ArrayList<Task> toDoList) {
        toDoList.add(this.task);
        System.out.println(printTaskAdd(this.task, toDoList));
    }
}
