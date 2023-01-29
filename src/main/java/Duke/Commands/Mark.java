package Duke.Commands;

import Duke.Commands.Tasks.Task;
import Duke.TaskList;

import java.util.ArrayList;

public class Mark extends Command {
    private final int index;

    public Mark(String message, int index) {
        super(message);
        this.index = index;
    }

    @Override
    public void execute(TaskList toDoList) {
        toDoList.get(this.index).markDone();
        System.out.println("Nice! I've marked this task as done:\n" + "    " +
                toDoList.get(this.index));
    }
}
