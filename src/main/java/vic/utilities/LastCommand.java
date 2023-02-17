package vic.utilities;

import java.util.ArrayList;

import vic.commands.ICommand;
import vic.tasks.ITask;


/**
 * LastCommand class to record last command performs
 */
public class LastCommand {
    private final ICommand.Type type;
    private final ITask task;

    /**
     * Constructor for LastCommand
     *
     * @param type type of command
     * @param task last task involve
     */
    public LastCommand(ICommand.Type type, ITask task) {
        this.task = task;
        this.type = type;
    }

    /**
     * Undoes the last command to the tasks list
     *
     * @param tasks reference to task list
     */
    public String undo(ArrayList<ITask> tasks) {
        String action = "";
        if (type == ICommand.Type.TODO) {
            action = "remove";
            tasks.remove(task);
        } else if (type == ICommand.Type.DELETE) {
            action = "add back";
            tasks.add(task);
        } else if (type == ICommand.Type.MARK || type == ICommand.Type.UNMARK) {
            action = "undo";

            int index = tasks.indexOf(task);
            tasks.set(index, task);
        }

        return "I have " + action + " to your task: \n" + task;
    }
}
