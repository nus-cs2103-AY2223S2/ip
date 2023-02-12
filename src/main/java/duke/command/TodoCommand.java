package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.gui.Ui;
import duke.Values;
import duke.task.Task;
import duke.task.ToDo;

/**
 * A Command subclass for the todo command.
 */
public class TodoCommand extends Command {
    @Override
    public String execute(Ui ui, TaskList list, String command) throws DukeException {
        String[] parts = command.split(Values.SPACEX);

        if (parts.length == 1) {
            throw new DukeException("ToDo description cannot be empty.");
        }

        // Get task name.
        String taskName = getName(parts);

        Task task = new ToDo(taskName.toString());
        list.addTask(task);

        return ui.pixlPrint("Added new todo!\n"
                + "\t" + task.formatTask()
                + "\nYou now have " + list.getSize() + " task(s) in the list.");
    }

    /**
     * Extracts the ToDo task name from the command.
     * @param parts
     * @return
     */
    private String getName(String[] parts) {
        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < parts.length; i++) {
            taskName.append(i == 1 ? "" : Values.SPACE);
            taskName.append(parts[i]);
        }
        return taskName.toString();
    }
}
