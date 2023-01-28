package Duke.Commands;

import Duke.Commands.Tasks.Task;

import java.util.ArrayList;

public class Unmark extends Command {
    private final int index;

    public Unmark(String message, int index) {
        super(message);
        this.index = index;
    }

    @Override
    public void execute(ArrayList<Task> toDoList) {
        toDoList.get(this.index).markUndone();
        System.out.println("OK, I've marked this task as not done yet:\n" + "    " +
                toDoList.get(this.index));
    }
}
