package duke.command;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;
import duke.gui.Ui;
import duke.Values;
import duke.task.Deadline;
import duke.task.Task;

/**
 * A Command subclass for the deadline command.
 */
public class DeadlineCommand extends Command {

    @Override
    public String execute(Ui ui, TaskList list, String command) throws DukeException {
        String[] parts = command.split(Values.SPACEX);
        int byIndex = Parser.getIndexOf(parts, "/by");
        int tagsIndex = Parser.getIndexOf(parts, "/tags");

        // Get name and due date of task.
        String taskName = getName(parts, byIndex);
        String dueDate = getDate(parts, byIndex);
        ArrayList<String> tags = extractTags(parts, tagsIndex);

        if (taskName.length() == 0 || dueDate.length() == 0) {
            throw new DukeException("Please provide both a deadline description and a due date.\n"
                    + "\tFormat: deadline <description> /by <due_date>");
        }

        // Convert date string to LocalDate
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(dueDate);
        } catch (RuntimeException re) {
            throw new DukeException("Could not parse date. Please use format 'yyyy-mm-dd'.");
        }

        Task task = new Deadline(taskName, localDate, tags);
        list.addTask(task);
        return ui.pixlPrint("Added new deadline!\n"
                + "\t" + task.formatTask()
                + "\nYou now have " + list.getSize() + " task(s) in the list.");
    }

    /**
     * Extracts the name of the deadline task.
     * @param parts Array of command's words (separated by spaces).
     * @param byIndex Index of "/by" in the command.
     * @return The name of the task.
     */
    private String getName(String[] parts, int byIndex) {
        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < byIndex; i++) {
            taskName.append(i == 1 ? "" : Values.SPACE);
            taskName.append(parts[i]);
        }
        return taskName.toString();
    }

    /**
     * Extracts the date of the deadline task.
     * @param parts Array of command's words (separated by spaces).
     * @param byIndex Index of "/by" in the command.
     * @return The date, as a String.
     */
    private String getDate(String[] parts, int byIndex) {
        StringBuilder dueDate = new StringBuilder();
        for (int i = byIndex + 1; i < parts.length; i++) {
            dueDate.append(i == byIndex + 1 ? "" : Values.SPACE);
            dueDate.append(parts[i]);
        }
        return dueDate.toString();
    }
}
