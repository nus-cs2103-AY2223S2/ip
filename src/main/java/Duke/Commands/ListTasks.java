package Duke.Commands;

import Duke.Commands.Tasks.Task;
import Duke.TaskList;

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
    public void execute(TaskList toDoList) {
        // empty
    }

    @Override
    public String toString() {
        return "Printing tasks:";
    }
}
