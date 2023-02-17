package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;
import duke.gui.Ui;
import duke.Values;
import duke.task.Event;
import duke.task.Task;

import java.util.ArrayList;

/**
 * A Command subclass for the event command.
 */
public class EventCommand extends Command {
    @Override
    public String execute(Ui ui, TaskList list, String command) throws DukeException {
        String[] parts = command.split(Values.SPACEX);
        int fromIndex = Parser.getIndexOf(parts, "/from");
        int toIndex = Parser.getIndexOf(parts, "/to");
        int tagsIndex = Parser.getIndexOf(parts, "/tags");

        // Get task name, start date, end date from the command.
        String taskName = getName(parts, fromIndex);
        String startDate = getStartDate(parts, fromIndex, toIndex);
        String endDate = tagsIndex == -1
                ? getEndDate(parts, toIndex, parts.length)
                : getEndDate(parts, toIndex, tagsIndex);
        ArrayList<String> tags = extractTags(parts, tagsIndex);

        if (taskName.length() == 0 || startDate.length() == 0 || endDate.length() == 0) {
            throw new DukeException("Please provide a description, start date, and end date.\n"
                    + "\tFormat: event <description> /from <start_date> /to <end_date>");
        }

        Task task = new Event(taskName, startDate, endDate, tags);
        list.addTask(task);

        return ui.pixlPrint("Added new event!\n"
                + "\t" + task.formatTask()
                + "\nYou now have " + list.getSize() + " task(s) in the list.");
    }

    /**
     * Extracts the name of the event task from the command.
     * @param parts Array of command's words (separated by spaces).
     * @param fromIndex Index of "/from" in the command.
     * @return The name of the event.
     */
    private String getName(String[] parts, int fromIndex) {
        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < fromIndex; i++) {
            taskName.append(i == 1 ? "" : Values.SPACE);
            taskName.append(parts[i]);
        }
        return taskName.toString();
    }

    /**
     * Extracts the start date of the event from the command.
     * @param parts Array of command's words (separated by spaces).
     * @param fromIndex Index of "/from" in the command.
     * @param toIndex Index of "/to" in the command.
     * @return The start date of the event, as a String.
     */
    private String getStartDate(String[] parts, int fromIndex, int toIndex) {
        StringBuilder startDate = new StringBuilder();
        for (int i = fromIndex + 1; i < toIndex; i++) {
            startDate.append(i == fromIndex + 1 ? "" : Values.SPACE);
            startDate.append(parts[i]);
        }
        return startDate.toString();
    }

    /**
     * Extracts the end date of the event from the command.
     * @param parts Array of command's words (separated by spaces).
     * @param toIndex Index of "/to" in the command.
     * @return The end date of the event, as a String.
     */
    private String getEndDate(String[] parts, int toIndex, int nextIndex) {
        StringBuilder endDate = new StringBuilder();
        for (int i = toIndex + 1; i < nextIndex; i++) {
            endDate.append(i == toIndex + 1 ? "" : Values.SPACE);
            endDate.append(parts[i]);
        }
        return endDate.toString();
    }
}
