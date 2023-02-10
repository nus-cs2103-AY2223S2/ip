package duke.commands;

import duke.TaskList;

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
        String res = "";
        for (int i = 0; i < toDoList.size(); i++) {
            res += String.format("%d.%s\n", i + 1, toDoList.get(i));
        }
        System.out.println("Printing tasks:\n" + res);
    }

    @Override
    public String toString() {
        return "";
    }
}
