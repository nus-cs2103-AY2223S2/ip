package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;
import duke.gui.Ui;
import duke.Values;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

/**
 * A Command subclass for the todo command.
 */
public class TodoCommand extends Command {
    @Override
    public String execute(Ui ui, TaskList list, String command) throws DukeException {
        String[] parts = command.split(Values.SPACEX);
        int tagsIndex = Parser.getIndexOf(parts, "/tags");

        if (parts.length == 1) {
            throw new DukeException("ToDo description cannot be empty.");
        }

        // Get task name.
        String taskName = getName(parts, tagsIndex);
        ArrayList<String> tags = extractTags(parts, tagsIndex);

        Task task = new ToDo(taskName, tags);
        list.addTask(task);

        return ui.pixlPrint("Added new todo!\n"
                + "\t" + task.formatTask()
                + "\nYou now have " + list.getSize() + " task(s) in the list.");
    }

    /**
     * Extracts the ToDo task name from the command.
     * @param parts Array of command's words (separated by spaces).
     * @param tagsIndex Index of "/tags" in the command.
     * @return The name of the task.
     */
    private String getName(String[] parts, int tagsIndex) {
        StringBuilder taskName = new StringBuilder();
        int end = tagsIndex == -1 ? parts.length : tagsIndex;
        for (int i = 1; i < end; i++) {
            taskName.append(i == 1 ? "" : Values.SPACE);
            taskName.append(parts[i]);
        }
        return taskName.toString();
    }
}
