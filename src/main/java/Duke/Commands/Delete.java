package Duke.Commands;

import Duke.Commands.Tasks.Task;

import java.util.ArrayList;

public class Delete extends Command {
    private final int index;
    public Delete(String message, int index) {
        super(message);
        this.index = index;
    }

    @Override
    public void execute(ArrayList<Task> toDoList) {
        System.out.println(String.format("Noted. I've removed this task:\n" +
                        "    %s\n" +
                        "Now you have %d tasks in the list.",
                toDoList.get(this.index), toDoList.size() - 1));
        toDoList.remove(this.index);
    }
}
