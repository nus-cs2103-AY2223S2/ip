package Duke.Commands;

import Duke.TaskList;

/**
 * This class represents the command to list all current tasks
 */
public class ListTasks extends Command {
    public ListTasks(String message) {
        super(message);
    }

    private static String getPrintOutput() {
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
