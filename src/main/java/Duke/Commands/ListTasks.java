package Duke.Commands;

import Duke.Commands.Tasks.Task;

import java.util.ArrayList;

public class ListTasks extends Command {
    public ListTasks(String message) {
        super(message);
    }

    private static String getPrintOutput() {
        // fix
        return "";
    }

    @Override
    public void execute(ArrayList<Task> toDoList) {
        // empty
    }

    @Override
    public String toString() {
        return "Printing tasks:";
    }
}
