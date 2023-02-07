package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;
import duke.gui.Ui;
import duke.Values;
import duke.task.Event;
import duke.task.Task;

/**
 * A Command subclass for the event command.
 */
public class EventCommand extends Command {
    @Override
    public String execute(Ui ui, TaskList list, String command) throws DukeException {
        String[] parts = command.split(Values.SPACEX);
        int fromIndex = Parser.getIndexOf(parts, "/from");
        int toIndex = Parser.getIndexOf(parts, "/to");

        // Get task name.
        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < fromIndex; i++) {
            taskName.append(i == 1 ? "" : Values.SPACE);
            taskName.append(parts[i]);
        }
        // Get start date.
        StringBuilder startDate = new StringBuilder();
        for (int i = fromIndex + 1; i < toIndex; i++) {
            startDate.append(i == fromIndex + 1 ? "" : Values.SPACE);
            startDate.append(parts[i]);
        }
        // Get end date.
        StringBuilder endDate = new StringBuilder();
        for (int i = toIndex + 1; i < parts.length; i++) {
            endDate.append(i == toIndex + 1 ? "" : Values.SPACE);
            endDate.append(parts[i]);
        }

        if (taskName.length() == 0 || startDate.length() == 0 || endDate.length() == 0) {
            throw new DukeException("Please provide a description, start date, and end date.\n"
                    + "\tFormat: event <description> /from <start_date> /to <end_date>");
        }

        Task task = new Event(taskName.toString(), startDate.toString(), endDate.toString());
        list.addTask(task);
        return ui.pixlPrint("Added new event!\n"
                + "\t" + task.formatTask()
                + "\nYou now have " + list.getSize() + " task(s) in the list.");
    }
}
