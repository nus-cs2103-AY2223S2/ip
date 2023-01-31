package command;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

import shigure.Ui;
import storage.Storage;
import task.Task;
import task.TaskList;

/**
 * A command printing the contents of a <code>TaskList</code> matching a given regular expression.
 */
public class Find implements Command {
    private String regex = "";

    /**
     * Creates a find command.
     *
     * @param regex regular expression to match against.
     */
    public Find(String regex) {
        this.regex = regex;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<SimpleEntry<Integer, Task>> outTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).hasMatchingObjective(regex)) {
                outTasks.add(new SimpleEntry<>(i, tasks.get(i)));
            }
        }
        ui.print("here's your " + outTasks.size() + (outTasks.size() == 1 ? " match:" : " matches:"));
        for (int i = 0; i < outTasks.size(); i++) {
            ui.print(outTasks.get(i).getKey() + 1 + ". " + outTasks.get(i).getValue());
        }
    }
}
